package q2;

import java.util.Arrays;

public class SentimentAnalyzer {
    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int featureOpinion = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
        if (featureOpinion == 0) {
            featureOpinion = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
        }
        return featureOpinion;
    }

    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        String pattern = feature + " was ";
        if (review.toLowerCase().contains(pattern)) {
            for (String posOpinion : posOpinionWords) {
                if (review.toLowerCase().contains(pattern + posOpinion)) {
                    return 1;
                }
            }
            for (String negOpinion : negOpinionWords) {
                if (review.toLowerCase().contains(pattern + negOpinion)) {
                    return -1;
                }
            }
        }
        return 0;
    }

    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        String[] sentences = review.split("\\.");
        for (String sentence : sentences) {
            for (String posWord : posOpinionWords) {
                if (sentence.toLowerCase().contains(posWord + " " + feature))
                    return 1;

            }
            for (String negWord : negOpinionWords) {
                if (sentence.toLowerCase().contains(negWord + " " + feature)) {
                    return -1;
                }
            }
        }
        return 0;
    }

    public static int[] detectProsAndCons(String review, String[][] featureSets, String[] posOpinionWords, String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSets.length];

        for (int i = 0; i < featureSets.length; i++) {
            String[] featureSet = featureSets[i];
            for (String feature : featureSet) {
                int opinion = getOpinionOnFeature(review, feature, posOpinionWords, negOpinionWords);
                if (opinion != 0) {
                    featureOpinions[i] = opinion;
                    break; // Move to the next feature set
                }
            }
        }

        return featureOpinions;
    }

    public static void main(String[] args) {
        String review = "Haven't been here in years! Fantastic service and the food was delicious! Definitely will be a frequent flyer! Francisco was very attentive";
        String[][] featureSet = {
                {"ambiance", "ambience", "atmosphere", "decor"},
                {"dessert", "ice cream", "desert"},
                {"food"},
                {"soup"},
                {"service", "management", "waiter", "waitress", "bartender", "staff", "server"}
        };
        String[] posOpinionWords = {"good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome", "delicious"};
        String[] negOpinionWords = {"slow", "bad", "horrible", "awful", "unprofessional", "poor"};

        int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
        Logs.slf4jLogger.debug("Opinions on Features: " + Arrays.toString(featureOpinions));
    }
}