package com.huce.library.modules.author;

import java.util.List;

public interface AuthorService {
    Author saveAuthor(Author author);

    Author getAuthorById(Long id);

    List<Author> getAllAuthors();

    Author updateAuthor(Long id, Author author);

    void deleteAuthor(Long id);
}
