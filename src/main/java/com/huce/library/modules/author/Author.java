package com.huce.library.modules.author;

import com.huce.library.modules.book.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "authors")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private Integer age;

    @JoinColumn
    @OneToMany
    private List<Book> books;
}
