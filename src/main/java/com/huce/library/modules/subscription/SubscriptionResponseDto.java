package com.huce.library.modules.subscription;

import com.huce.library.modules.book.Book;
import com.huce.library.modules.book.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
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
    private Integer rentLimit;
    private List<BookDto> rentedBooks;
    private String status;

    public SubscriptionResponseDto(Subscription subscription) {
        setId(subscription.getId());
        setUser(subscription.getUser().getId());
        setStartDate(subscription.getStartDate());
        setEndDate(subscription.getEndDate());
        setPeriod(subscription.getPeriod());
        setRentLimit(subscription.getRentLimit());
        setStatus(subscription.getStatus());
    }
}
