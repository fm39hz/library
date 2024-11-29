package com.huce.library.modules.book;

import java.util.List;

public interface BookService {
    Book saveBook(BookDto book);

    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book updateBook(Long id, BookDto book);

    void deleteBook(Long id);
}