package com.huce.library.modules.subscription;

import com.huce.library.modules.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class SubscriptionRequestDto {
    private Long user;
    private Date startDate;
    private Integer period;
    private List<Book> rentedBooks;
    private String status;

    public SubscriptionRequestDto(Subscription subscription) {
        setUser(subscription.getUser().getId());
        setStartDate(subscription.getStartDate());
        setPeriod(subscription.getPeriod());
        setRentedBooks(subscription.getRentedBooks());
        setStatus(subscription.getStatus());
    }
}
