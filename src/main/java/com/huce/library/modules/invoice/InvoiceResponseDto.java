package com.huce.library.modules.invoice;

import com.huce.library.modules.book.BookDto;
import com.huce.library.modules.subscription.Subscription;
import com.huce.library.modules.subscription.SubscriptionResponseDto;
import lombok.Data;

import java.util.Date;

@Data
public class InvoiceResponseDto {
    private Long id;
    private Date rentDate;
    private Date exceedDate;
    private BookDto book;
    private SubscriptionResponseDto subscription;
    public InvoiceResponseDto(Invoice invoice) {
        setId(invoice.getId());
        setRentDate(invoice.getRentDate());
        setExceedDate(invoice.getExceedDate());
        setBook(new BookDto(invoice.getBook()));
        setSubscription(new SubscriptionResponseDto(invoice.getSubscription()));
    }
}
