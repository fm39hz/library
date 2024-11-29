package com.huce.library.modules.author;

import java.util.List;

public interface AuthorService {
    Author saveAuthor(AuthorDto author);

    Author getAuthorById(Long id);

    List<Author> getAllAuthors();

    Author updateAuthor(Long id, AuthorDto author);

    void deleteAuthor(Long id);
}
