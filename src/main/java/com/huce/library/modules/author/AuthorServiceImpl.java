package com.huce.library.modules.author;

import com.huce.library.exception.ResourceNotFoundException;
import com.huce.library.modules.book.Book;
import com.huce.library.modules.book.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Author saveAuthor(AuthorDto author) {
        Author newAuthor = new Author();
        List<Book> books = new ArrayList<>();
        for (Long bookId : author.getBooks()) {
            if (bookRepository.findById(bookId).isPresent()) {
                Book book = bookRepository.findById(bookId).get();
                books.add(book);
            }
        }
        newAuthor.setId(author.getId());
        newAuthor.setName(author.getName());
        newAuthor.setAge(author.getAge());
        newAuthor.setBooks(books);
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
    public Author updateAuthor(Long id, AuthorDto authorDetails) {
        Author author = getAuthorById(id);
        Author newAuthor = saveAuthor(authorDetails);
        newAuthor.setId(author.getId());
        author.setName(authorDetails.getName());
        author.setAge(authorDetails.getAge());
        List<Book> books = new ArrayList<>();
        for (Long bookId : authorDetails.getBooks()) {
            if (bookRepository.findById(bookId).isPresent()) {
                Book book = bookRepository.findById(bookId).get();
                books.add(book);
            }
        }
        author.setBooks(books);
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }
}
