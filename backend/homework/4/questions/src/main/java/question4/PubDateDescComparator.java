package question4;

import java.util.Comparator;

public class PubDateDescComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        if (book1.getYear() != book2.getYear()) {
            return Integer.compare(book2.getYear(), book1.getYear());
        } else {
            return book1.getTitle().compareTo(book2.getTitle());
        }
    }
}
