package com.example.handson4.entity;

import com.example.handson4.dto.BookResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String id;
    private String title;
    private String author;
    private String isbn;
    private String language;
    private int price;
    public Book(BookResponseDto bookResponseDto) {
        this.id = bookResponseDto.getId();
        this.title = bookResponseDto.getTitle();
        this.author = bookResponseDto.getAuthor();
        this.isbn = bookResponseDto.getIsbn();
        this.language = bookResponseDto.getLanguage();
        this.price = bookResponseDto.getPrice();
    }
}
