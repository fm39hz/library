package com.huce.library.module.author;

import java.util.List;

public interface AuthorService {
    Author saveAuthor(AuthorRequestDto author);

    Author getAuthorById(Long id);

    List<Author> getAllAuthors();

    Author updateAuthor(Long id, AuthorRequestDto author);

    void deleteAuthor(Long id);
}
