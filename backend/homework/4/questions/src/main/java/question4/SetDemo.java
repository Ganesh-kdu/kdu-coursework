package question4;

import com.ganesh.LogMaster;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;



public class SetDemo {

    public static void treeSetDemo(Comparator<Book> comparator) {
        Set<Book> books;

        if (comparator == null) {
            books = new TreeSet<>();
        } else {
            books = new TreeSet<>(comparator);
        }

        Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        for (Book book : books) {
            System.out.println(book);
        }

    }

    public static void main(String[] args) {

        LogMaster.print("i) treeSetDemo(null):");
        treeSetDemo(null);

        LogMaster.print("\nii) treeSetDemo(new PubDateAscComparator()):");
        treeSetDemo(new PubDateAscComparator());

        LogMaster.print("\niii) treeSetDemo(new PubDateDescComparator()):");
        treeSetDemo(new PubDateDescComparator());
    }
}