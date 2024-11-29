package com.huce.library.modules.subscription;

import com.huce.library.modules.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class SubscriptionResponseDto {
    private Long id;
    private Long user;
    private Date startDate;
    private Date endDate;
    private Integer period;
    private List<Book> rentedBooks;
    private String status;

    public SubscriptionResponseDto(Subscription subscription) {
        setId(subscription.getId());
        setUser(subscription.getUser().getId());
        setStartDate(subscription.getStartDate());
        setEndDate(subscription.getEndDate());
        setPeriod(subscription.getPeriod());
        setRentedBooks(subscription.getRentedBooks());
        setStatus(subscription.getStatus());
    }
}
