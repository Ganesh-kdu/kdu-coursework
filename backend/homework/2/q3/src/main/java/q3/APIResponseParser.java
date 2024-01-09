package q3;

public class APIResponseParser {
    public static Book parse(String response) {
        Book book = new Book();
        String endRule = "<";

        // Parse title
        String startTitleRule = "<title>";
        String title = parse(response, startTitleRule, endRule);
        book.setTitle(title);

        // Parse author name
        String[] startAuthorRule = {"<author>", "<name>"};
        String authorName = parse(response, startAuthorRule, "</name>");
        book.setAuthor(authorName);

        // Parse publication year
        String startYearRule = "<original_publication_year type=\"integer\">";
        int publicationYear = Integer.parseInt(parse(response, startYearRule, "</original_publication_year>"));
        book.setPublicationYear(publicationYear);

        // Parse average rating
        String startRatingRule = "<average_rating>";
        double averageRating = Double.parseDouble(parse(response, startRatingRule, "</average_rating>"));
        book.setAverageRating(averageRating);

        // Parse ratings count
        String startRatingsCountRule = "<ratings_count type=\"integer\">";
        String ratingsCountString = parse(response, startRatingsCountRule, "</ratings_count>");
        int ratingsCount = Integer.parseInt(ratingsCountString.replaceAll(",", ""));
        book.setRatingsCount(ratingsCount);

        // Parse image URL
        String startImageUrlRule = "<image_url>";
        String imageUrl = parse(response, startImageUrlRule, "</image_url>");
        book.setImageUrl(imageUrl);

        // Your additional parsing code goes here

        return book;
    }

    //Overloaded parse method with three parameters: response, startRule, and endRule.
    private static String parse(String response, String startRule, String endRule) {
        int startIndex = response.indexOf(startRule) + startRule.length();
        int endIndex = response.indexOf(endRule, startIndex);
        return response.substring(startIndex, endIndex);
    }

    //Overloaded parse method with an array of startRules and endRule.
    private static String parse(String response, String[] startRules, String endRule) {
        int startIndex = response.indexOf(startRules[0]);
        for (int i = 1; i < startRules.length; i++) {
            startIndex = response.indexOf(startRules[i], startIndex);
        }
        startIndex += startRules[startRules.length - 1].length();

        int endIndex = response.indexOf(endRule, startIndex);
        return response.substring(startIndex, endIndex);
    }

    public static void main(String[] args) {
        String response = "<work>" +
                "<id type=\"integer\">2361393</id>" +
                "<books_count type=\"integer\">813</books_count>" +
                "<ratings_count type=\"integer\">1,16,315</ratings_count>" +
                "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
                "<original_publication_year type=\"integer\">1854</original_publication_year>" +
                "<original_publication_month type=\"integer\" nil=\"true\"/>" +
                "<original_publication_day type=\"integer\" nil=\"true\"/>" +
                "<average_rating>3.79</average_rating>" +
                "<best_book type=\"Book\">" +
                "<id type=\"integer\">16902</id>" +
                "<title>Walden</title>" +
                "<author>" +
                "<id type=\"integer\">10264</id>" +
                "<name>Henry David Thoreau</name>" +
                "</author>" +
                "<image_url>" +
                "http://images.gr-assets.com/books/1465675526m/16902.jpg" +
                "</image_url>" +
                "<small_image_url>" +
                "http://images.gr-assets.com/books/1465675526s/16902.jpg" +
                "</small_image_url>" +
                "</best_book>" +
                "</work>";
        Book book = APIResponseParser.parse(response);

        // Print book information
        Logs.slf4jLogger.debug("Book Information:");
        Logs.slf4jLogger.debug("Title: " + book.getTitle());
        Logs.slf4jLogger.debug("Author: " + book.getAuthor());
        Logs.slf4jLogger.debug("Publication Year: " + book.getPublicationYear());
        Logs.slf4jLogger.debug("Average Rating: " + book.getAverageRating());
        Logs.slf4jLogger.debug("Ratings Count: " + book.getRatingsCount());
        Logs.slf4jLogger.debug("Image URL: " + book.getImageUrl());


    }
}