package com.huce.library.module.book;

import java.util.List;

public interface BookService {
    Book saveBook(BookRequestDto book);

    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book updateBook(Long id, BookRequestDto book);

    void deleteBook(Long id);
}