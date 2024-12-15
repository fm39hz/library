package com.huce.library.module.record;

import com.huce.library.module.book.BookResponseDto;
import com.huce.library.module.subscription.SubscriptionResponseDto;
import lombok.Data;

import java.util.Date;

@Data
public class RecordResponseDto {
    private Long id;
    private Date rentDate;
    private Date exceedDate;
    private BookResponseDto book;
    private SubscriptionResponseDto subscription;

    public RecordResponseDto(Record record) {
        setId(record.getId());
        setRentDate(record.getRentDate());
        setExceedDate(record.getExceedDate());
        setBook(new BookResponseDto(record.getBook()));
        setSubscription(new SubscriptionResponseDto(record.getSubscription()));
    }
}
