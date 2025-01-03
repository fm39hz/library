package com.huce.library.module.book;

import com.huce.library.module.user.Roles;
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
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookResponseDto> bookDtos = books.stream().map(BookResponseDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookDtos);
    }

    @GetMapping("/{id}")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new BookResponseDto(bookService.getBookById(id)));
    }

    @PostMapping("/")
    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(new BookResponseDto(savedBook), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody BookRequestDto book) {
        return ResponseEntity.ok().body(new BookResponseDto(bookService.updateBook(id, book)));
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
