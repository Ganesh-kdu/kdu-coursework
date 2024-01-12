package question4;

import java.util.Objects;

public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private int year;

    // Constructors
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // toString, hashCode, and equals methods
    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", year=" + year + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book other = (Book) obj;
        return year == other.year &&
                Objects.equals(title, other.title) &&
                Objects.equals(author, other.author);
    }

    // compareTo method for Comparable interface
    @Override
    public int compareTo(Book otherBook) {
        return title.compareTo(otherBook.getTitle());
    }
}