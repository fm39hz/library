package com.huce.library.module.book;

import com.huce.library.exception.ResourceNotFoundException;
import com.huce.library.module.author.Author;
import com.huce.library.module.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Book saveBook(BookRequestDto book) {
        Book newBook = new Book();
        newBook.setTitle(book.getName());
        newBook.setDescription(book.getDescription());
        newBook.setImage(book.getImage());
        newBook.setInStock(book.getInStock());
        newBook.setAuthor(setAuthor(book));
        return bookRepository.save(newBook);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            return bookRepository.findById(id).get();
        }
        throw new ResourceNotFoundException("Book Not Found");
    }

    @Override
    public Book updateBook(Long id, BookRequestDto bookDetails) {
        Book book = getBookById(id);
        book.setTitle(book.getTitle());
        book.setImage(bookDetails.getImage());
        book.setInStock(bookDetails.getInStock());
        book.setDescription(bookDetails.getDescription());
        book.setAuthor(setAuthor(bookDetails));
        return bookRepository.save(book);
    }

    private Author setAuthor(BookRequestDto bookDetails) {
        Author author;
        if (authorRepository.findById(bookDetails.getAuthorId()).isEmpty()) {
            author = new Author();
            author.setId(bookDetails.getAuthorId());
            authorRepository.save(author);
        } else {
            author = authorRepository.findById(bookDetails.getAuthorId()).get();
        }
        return author;
    }

    @Override
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
}
