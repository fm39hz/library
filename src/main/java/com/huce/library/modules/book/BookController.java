package com.huce.library.modules.book;

import com.huce.library.modules.user.Roles;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDto> bookDtos = books.stream().map(BookDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookDtos);
    }

    @GetMapping("/{id}")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new BookDto(bookService.getBookById(id)));
    }

    @PostMapping("/")
    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(new BookDto(savedBook), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto book) {
        return ResponseEntity.ok().body(new BookDto(bookService.updateBook(id, book)));
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
