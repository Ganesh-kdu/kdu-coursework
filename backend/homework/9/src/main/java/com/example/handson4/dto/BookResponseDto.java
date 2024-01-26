package com.example.handson4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private String id;
    private String title;
    private String author;
    private String isbn;
    private String language;
    private int price;
}
