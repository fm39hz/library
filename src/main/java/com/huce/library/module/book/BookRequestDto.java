package com.huce.library.module.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class BookRequestDto {
    private String name;
    private String description;
    private Integer inStock;
    private String image;
    private Long authorId;

    public BookRequestDto(@NotNull Book book) {
        setName(book.getTitle());
        setDescription(book.getDescription());
        setInStock(book.getInStock());
        setAuthorId(book.getAuthor().getId());
        setImage(book.getImage());
    }
}
