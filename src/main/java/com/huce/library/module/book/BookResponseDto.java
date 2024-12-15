package com.huce.library.module.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class BookResponseDto {
    private Long id;
    private String title;
    private String description;
    private Integer inStock;
    private String image;
    private Long authorId;

    public BookResponseDto(@NotNull Book book) {
        setId(book.getId());
        setTitle(book.getTitle());
        setDescription(book.getDescription());
        setInStock(book.getInStock());
        setAuthorId(book.getAuthor().getId());
        setImage(book.getImage());
    }
}
