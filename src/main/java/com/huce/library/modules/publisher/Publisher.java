package com.huce.library.modules.publisher;

import com.huce.library.modules.book.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "publishers")
@Data
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String address;

    @OneToMany()
    @JoinTable(
            name = "publisher_books",
            joinColumns = @JoinColumn(name = "publisher_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;
}
