package com.huce.library.modules.subscription;

import com.huce.library.modules.book.Book;
import com.huce.library.modules.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SubscriptionUpdateDto {
    private Long id;
    private User user;
    private Integer period;
    private List<Book> rentedBooks;
    private String status;

    public SubscriptionUpdateDto(Subscription subscription) {
        setId(subscription.getId());
        setUser(subscription.getUser());
        setRentedBooks(subscription.getRentedBooks());
        setStatus(subscription.getStatus());
    }
}
