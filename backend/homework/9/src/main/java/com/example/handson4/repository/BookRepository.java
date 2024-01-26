package com.example.handson4.repository;

import com.example.handson4.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class BookRepository {
    private HashMap<String, Book> bookList;
    public BookRepository() {
        bookList = new HashMap<>();
    }
    public Book createBook(Book book) {
        bookList.put(book.getIsbn(),book);
        return book;
    }

    public Book getBook(String isbn) {
        return bookList.get(isbn);
    }

    public Book updateBook(String isbn, Book book) {
        if(bookList.computeIfPresent(isbn,(k,v)->book)!=null){
            return book;
        }
        return null;
    }

    public Book deleteBook(String isbn) {
        if(bookList.get(isbn)!=null){
            return bookList.remove(isbn);
        }
        return null;
    }
}
