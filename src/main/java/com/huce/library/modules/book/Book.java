package com.huce.library.modules.book;

import com.huce.library.modules.author.Author;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer inStock;

    @Column()
    private String image;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}