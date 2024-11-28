package com.huce.library.modules.author;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {
    Author saveAuthor(Author author);

    ResponseEntity<Author> getAuthorById(Long id);

    ResponseEntity<List<Author>> getAllAuthors();

    ResponseEntity<Author> updateAuthor(Long id, Author author);

    ResponseEntity<Void> deleteAuthor(Long id);
}
