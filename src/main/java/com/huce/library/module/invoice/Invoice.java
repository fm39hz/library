package com.huce.library.module.invoice;

import com.huce.library.module.payment.PaymentStatus;
import com.huce.library.module.payment.vnpay.VnPayRequestDto;
import com.huce.library.module.subscription.Subscription;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Float amount;

    @Column(nullable = false)
    private Date createTime;

    @Column(nullable = false)
    private String status = PaymentStatus.WAITING;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
}
