package com.huce.library.module.subscription;

import com.huce.library.module.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Integer period = 0;

    @Column(nullable = false)
    private Float lateFeePercent = 0.25f;

    @Column(nullable = false)
    private Float lateFee = 15f;

    @Column(nullable = false)
    private Float remainingFee;

    @Column(nullable = false)
    private Integer rentLimit = 5;

    public Subscription(Long id) {
        setId(id);
    }
}
