package com.huce.library.modules.author;

import com.huce.library.modules.user.Roles;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<Author> createBook(@RequestBody Author author) {
        Author savedAuthor = authorService.saveAuthor(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @GetMapping("/")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<List<Author>> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PutMapping("/{id}")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        return authorService.deleteAuthor(id);
    }
}
