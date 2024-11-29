package com.huce.library.modules.author;

import com.huce.library.modules.book.Book;
import com.huce.library.modules.book.BookDto;
import com.huce.library.modules.user.Roles;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        List<AuthorDto> authorDtos = authors.stream().map(AuthorDto::new).collect(Collectors.toList());
        return new ResponseEntity<>(authorDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new AuthorDto(authorService.getAuthorById(id)));
    }

    @GetMapping("/{id}/books")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<List<BookDto>> getAuthorBooks(@PathVariable Long id) {
        List<Book> books = authorService.getAuthorById(id).getBooks();
        List<BookDto> bookDtos = books.stream().map(BookDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookDtos);
    }

    @PostMapping("/")
    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<AuthorDto> createBook(@RequestBody AuthorDto author) {
        Author savedAuthor = authorService.saveAuthor(author);
        return new ResponseEntity<>(new AuthorDto(savedAuthor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto author) {
        return ResponseEntity.ok().body(new AuthorDto(authorService.updateAuthor(id, author)));
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
