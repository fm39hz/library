package com.huce.library.module.publisher;

import com.huce.library.module.book.Book;
import com.huce.library.module.book.BookDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PublisherDto {
    private Long id;
    private String name;
    private List<BookDto> books;

    public PublisherDto(Publisher publisher) {
        this.id = publisher.getId();
        this.name = publisher.getName();
        this.books = new ArrayList<>();
        for (Book book : publisher.getBooks()) {
            this.books.add(new BookDto(book));
        }
    }
}
