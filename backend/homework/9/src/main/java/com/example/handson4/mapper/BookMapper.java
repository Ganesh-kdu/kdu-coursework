package com.example.handson4.mapper;

import com.example.handson4.dto.BookResponseDto;
import com.example.handson4.entity.Book;

import java.util.Objects;

public class BookMapper {
    private BookMapper() {
    }
    public static BookResponseDto toResponseDto(Book book) {
        if (Objects.isNull(book)){
            return null;
        }
        return new BookResponseDto(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getLanguage(), book.getPrice());
    }
}
