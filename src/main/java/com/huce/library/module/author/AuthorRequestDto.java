package com.huce.library.module.author;

import com.huce.library.module.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class AuthorRequestDto {
    private String name;
    private Integer age;
    private List<Long> books;

    public AuthorRequestDto(@NotNull Author author) {
        List<Long> books = new ArrayList<>();
        for (Book book : author.getBooks()) {
            books.add(book.getId());
        }
        setName(author.getName());
        setAge(author.getAge());
        setBooks(books);
    }
}
