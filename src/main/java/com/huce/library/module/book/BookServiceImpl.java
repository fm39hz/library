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
    public Book saveBook(BookDto book) {
        if (authorRepository.findById(book.getAuthorId()).isEmpty()) {
            Author author = new Author();
            author.setId(book.getAuthorId());
            authorRepository.save(author);
        }
        Book newBook = new Book();
        Author author = authorRepository.findById(book.getAuthorId()).get();
        newBook.setAuthor(author);
        newBook.setTitle(book.getTitle());
        newBook.setDescription(book.getDescription());
        newBook.setImage(book.getImage());
        newBook.setInStock(book.getInStock());
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
    public Book updateBook(Long id, BookDto bookDetails) {
        if (authorRepository.findById(bookDetails.getAuthorId()).isEmpty()) {
            Author author = new Author();
            author.setId(bookDetails.getAuthorId());
            authorRepository.save(author);
        }
        Book book = getBookById(id);
        Author author = authorRepository.findById(bookDetails.getAuthorId()).get();
        book.setAuthor(author);
        book.setTitle(book.getTitle());
        book.setDescription(bookDetails.getDescription());
        book.setInStock(bookDetails.getInStock());
        book.setImage(bookDetails.getImage());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
}
