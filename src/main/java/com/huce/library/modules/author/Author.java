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

    @Column()
    private Integer age;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;
}
