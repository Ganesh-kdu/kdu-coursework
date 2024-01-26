package com.example.handson4.service;

import com.example.handson4.constants.Constants;
import com.example.handson4.dto.BookRequestDto;
import com.example.handson4.dto.BookResponseDto;
import com.example.handson4.entity.Book;
import com.example.handson4.exception.custom.ObjectAlreadyExistsException;
import com.example.handson4.exception.custom.ObjectNotFoundException;
import com.example.handson4.mapper.BookMapper;
import com.example.handson4.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class BookService {
    private final Logger logger = LoggerFactory.getLogger(BookService.class);
    private BookRepository bookRepository;
    @Autowired
    public BookService(){
        bookRepository = new BookRepository();
    }

    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        String id = UUID.randomUUID().toString();
        if (!Objects.isNull(bookRepository.getBook(bookRequestDto.getIsbn()))) {
            logger.error(Constants.EXISTS);
            throw new ObjectAlreadyExistsException(Constants.EXISTS);
        }
        Book book = new Book(id, bookRequestDto.getTitle(), bookRequestDto.getAuthor(), bookRequestDto.getIsbn(), bookRequestDto.getLanguage(), bookRequestDto.getPrice());
        return BookMapper.toResponseDto(bookRepository.createBook(book));
    }

    public BookResponseDto getBook(String isbn) throws ObjectNotFoundException {
        Book book = bookRepository.getBook(isbn);
        if (book == null) {
            logger.error(Constants.NOT_FOUND_WITH_ISBN, isbn);
            throw new ObjectNotFoundException(Constants.NOT_FOUND);
        }
        return BookMapper.toResponseDto(book);
    }

    public BookResponseDto updateBook(String isbn, BookRequestDto bookRequestDto) {
        Book oldBook = bookRepository.getBook(isbn);
        if (oldBook == null) {
            logger.error(Constants.NOT_FOUND_WITH_ISBN, isbn);
            throw new ObjectNotFoundException(Constants.NOT_FOUND);
        }
        Book book = new Book(oldBook.getId(), bookRequestDto.getTitle(), bookRequestDto.getAuthor(), bookRequestDto.getIsbn(), bookRequestDto.getLanguage(), bookRequestDto.getPrice());
        return BookMapper.toResponseDto(bookRepository.updateBook(isbn, book));
    }

    public BookResponseDto deleteBook(String isbn) {
        Book book = bookRepository.getBook(isbn);
        if (Objects.isNull(book)) {
            logger.error(Constants.NOT_FOUND_WITH_ISBN, isbn);
            throw new ObjectNotFoundException(Constants.NOT_FOUND);
        }
        return BookMapper.toResponseDto(bookRepository.deleteBook(isbn));
    }
}
