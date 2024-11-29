package com.huce.library.modules.subscription;

import com.huce.library.modules.book.Book;
import com.huce.library.modules.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "subscriptions")
@Data
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Date startDate = new Date();

    @Column(nullable = false)
    private Date endDate = new Date();

    @Column(nullable = false)
    private String status = "pending";

    @Column(nullable = false)
    private Integer period = 1;

    @ManyToMany(mappedBy = "rented")
    private List<Book> rentedBooks;

    public Subscription(Long id) {
        setId(id);
    }
}
