package com.huce.library.module.record;

import com.huce.library.module.book.BookDto;
import com.huce.library.module.subscription.SubscriptionResponseDto;
import lombok.Data;

import java.util.Date;

@Data
public class RecordResponseDto {
    private Long id;
    private Date rentDate;
    private Date exceedDate;
    private BookDto book;
    private SubscriptionResponseDto subscription;

    public RecordResponseDto(Record record) {
        setId(record.getId());
        setRentDate(record.getRentDate());
        setExceedDate(record.getExceedDate());
        setBook(new BookDto(record.getBook()));
        setSubscription(new SubscriptionResponseDto(record.getSubscription()));
    }
}
