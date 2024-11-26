package com.huce.library.modules.book;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book saveBook(Book book);

    ResponseEntity<List<Book>> getAllBooks();

    Optional<Book> getBookById(Long id);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);
}