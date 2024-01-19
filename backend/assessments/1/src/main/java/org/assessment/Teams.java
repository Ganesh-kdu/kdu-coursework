package org.assessment;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Teams {
    private HashMap<String, Team> teams;
    private ArrayList<String> teamNames;
    private ArrayList<Player> players;

    public void addPlayer(String team, Player player){
        players.add(player);
        teams.computeIfAbsent(team, k -> new Team());
        teams.get(team).addPlayer(player);
    }

    public List<String> getBowlersOverForty(String team){
        return teams.get(team).atLeastForty();
    }

    public List<Player> getBestOfTeam(String team){
        Log.customLogger("Run Scorer:" + teams.get(team).getRunScorer().getName(),"INFO");
        Log.customLogger("Run Scorer:" + teams.get(team).getWicketTaker().getName(),"INFO");
        return List.of(new Player[]{teams.get(team).getRunScorer(), teams.get(team).getWicketTaker()});
    }

    static class WicketComparator implements Comparator<Object> {
        public int compare(Object o1,Object o2){
            Player s1=(Player) o1;
            Player s2=(Player)o2;
            if(s1.getWickets().equals(s2.getWickets()))
                return 0;
            else if(s1.getWickets()<s2.getWickets())
                return 1;
            else
                return -1;
        }
    }
    public List<Player> top3Wicket(){
        players.sort(new WicketComparator());
        players.subList(0,3).forEach(u -> Log.customLogger(u.getName()+u.getWickets(),"INFO")
        );
        return  players.subList(0,3);
    }

    static class RunComparator implements Comparator<Object> {
        public int compare(Object o1,Object o2){
            Player s1=(Player) o1;
            Player s2=(Player)o2;
            if(s1.getRuns().equals(s2.getRuns()))
                return 0;
            else if(s1.getRuns()<s2.getRuns())
                return 1;
            else
                return -1;
        }
    }
    public List<Player> top3Runs(){
        players.sort(new RunComparator());
        players.subList(0,3).forEach(u -> Log.customLogger(u.getName()+u.getRuns(),"INFO")
        );
        return  players.subList(0,3);
    }
    public List<String> getTeamNames() {
        return teamNames;
    }

    public Teams(String path) {
        FileReader fileReader = null;
        teams = new HashMap<>();
        players = new ArrayList<>();
        HashSet<String> teamNamesSet = new HashSet<>();
        try {
            fileReader = new FileReader(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
        String[] nextRecord;
        try {
            while  ((nextRecord = csvReader.readNext())!=null){
                Player player = new Player(nextRecord[0],nextRecord[1],nextRecord[2],Integer.parseInt(nextRecord[3]),Integer.parseInt(nextRecord[4]),Double.parseDouble(nextRecord[5]),Double.parseDouble(nextRecord[6]),Integer.parseInt(nextRecord[7]));
                teamNamesSet.add(nextRecord[1]);
                addPlayer(nextRecord[1], player);
            }}catch (IOException e) {
            throw new RuntimeException(e);
        }
//        this.teamNames = new ArrayList<>(teamNames) ;
    }
}
