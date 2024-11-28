package com.huce.library.modules.book;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String description;
    private Integer inStock;
    private Long authorId;

    public BookDto(Book book) {
        setId(book.getId());
        setTitle(book.getTitle());
        setDescription(book.getDescription());
        setInStock(book.getInStock());
        setAuthorId(book.getAuthor().getId());
    }
}
