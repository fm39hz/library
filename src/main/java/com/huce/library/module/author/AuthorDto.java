package com.huce.library.module.author;

import com.huce.library.module.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class AuthorDto {
    private Long id;
    private String name;
    private Integer age;
    private List<Long> books;

    public AuthorDto(@NotNull Author author) {
        List<Long> books = new ArrayList<>();
        for (Book book : author.getBooks()) {
            books.add(book.getId());
        }
        setId(author.getId());
        setName(author.getName());
        setAge(author.getAge());
        setBooks(books);
    }
}
