package com.huce.library.module.record;

import com.huce.library.module.book.Book;
import com.huce.library.module.subscription.Subscription;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date rentDate;

    @Column(nullable = false)
    private Date exceedDate;

    @Column(nullable = false)
    private String status = "renting";

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
}
