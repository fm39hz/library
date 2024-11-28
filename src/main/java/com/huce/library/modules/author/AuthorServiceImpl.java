package com.huce.library.modules.author;

import com.huce.library.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public ResponseEntity<Author> getAuthorById(Long id) {
        return ResponseEntity.ok().body(authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id)));
    }

    @Override
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok().body(authorRepository.findAll());
    }

    @Override
    public ResponseEntity<Author> updateAuthor(Long id, Author authorDetails) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));

        author.setName(authorDetails.getName());
        author.setAge(authorDetails.getAge());
        author.setBooks(authorDetails.getBooks());

        return ResponseEntity.ok().body(authorRepository.save(author));
    }

    @Override
    public ResponseEntity<Void> deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
        authorRepository.delete(author);
        return ResponseEntity.noContent().build();
    }
}
