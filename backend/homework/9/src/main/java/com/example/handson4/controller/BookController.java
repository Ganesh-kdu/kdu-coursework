package com.example.handson4.controller;

import com.example.handson4.dto.BookRequestDto;
import com.example.handson4.dto.BookResponseDto;
import com.example.handson4.exception.custom.ObjectNotFoundException;
import com.example.handson4.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(){
        bookService = new BookService();
    }


    @PostMapping("/create-book")
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.createBook(bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, Objects.isNull(bookResponseDto)? HttpStatus.CONFLICT : HttpStatus.CREATED);
    }

    @GetMapping("/get-book/{isbn}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable String isbn) throws ObjectNotFoundException {
        BookResponseDto bookResponseDto = bookService.getBook(isbn);
        return new ResponseEntity<>(bookResponseDto, Objects.isNull(bookResponseDto) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PutMapping("/update-book-isbn/{isbn}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable String isbn, @RequestBody BookRequestDto bookRequestDto) throws ObjectNotFoundException {
        BookResponseDto bookResponseDto = bookService.updateBook(isbn, bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, Objects.isNull(bookResponseDto) ? HttpStatus.NOT_MODIFIED : HttpStatus.OK);
    }

    @DeleteMapping("/delete-book/{isbn}")
    public ResponseEntity<BookResponseDto> deleteBook(@PathVariable String isbn) throws ObjectNotFoundException {
        BookResponseDto bookResponseDto = bookService.deleteBook(isbn);
        return new ResponseEntity<>(bookResponseDto, Objects.isNull(bookResponseDto) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }


}
