package com.huce.library.modules.book;

import com.huce.library.modules.author.Author;
import com.huce.library.modules.publisher.Publisher;
import com.huce.library.modules.subscription.Subscription;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @ManyToMany()
    private List<Subscription> rented;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;
}