package com.huce.library.module.author;

import com.huce.library.exception.ResourceNotFoundException;
import com.huce.library.module.book.Book;
import com.huce.library.module.book.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public Author saveAuthor(AuthorRequestDto author) {
        Author newAuthor = new Author();
        for (Long bookId : author.getBooks()) {
            if (bookRepository.findById(bookId).isPresent()) {
                Book book = bookRepository.findById(bookId).get();
                newAuthor.getBooks().add(book);
            }
        }
        newAuthor.setName(author.getName());
        newAuthor.setAge(author.getAge());
        return authorRepository.save(newAuthor);
    }

    @Override
    public Author getAuthorById(Long id) {
        if (authorRepository.findById(id).isPresent()) {
            return authorRepository.findById(id).get();
        }
        throw new ResourceNotFoundException("Author Not Found");
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author updateAuthor(Long id, AuthorRequestDto authorDetails) {
        Author author = getAuthorById(id);
        author.setName(authorDetails.getName());
        author.setAge(authorDetails.getAge());
        for (Long bookId : authorDetails.getBooks()) {
            if (bookRepository.findById(bookId).isPresent()) {
                Book book = bookRepository.findById(bookId).get();
                author.getBooks().add(book);
            }
        }
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }
}
