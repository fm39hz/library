package com.huce.library.module.subscription;

import com.huce.library.exception.RequestDeniedException;
import com.huce.library.exception.ResourceNotFoundException;
import com.huce.library.module.book.Book;
import com.huce.library.module.book.BookRepository;
import com.huce.library.module.book.BookRequestDto;
import com.huce.library.module.book.BookService;
import com.huce.library.module.record.Record;
import com.huce.library.module.record.RecordRepository;
import com.huce.library.module.user.User;
import com.huce.library.module.user.UserRepository;
import com.huce.library.util.CalendarUtil;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final RecordRepository recordRepository;
    private final BookService bookService;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, RecordRepository recordRepository, BookService bookService, UserRepository userRepository, BookRepository bookRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.recordRepository = recordRepository;
        this.bookService = bookService;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Subscription createSubscription(SubscriptionRequestDto subscriptionDto) {
        Subscription subscription = new Subscription();
        if (userRepository.findById(subscriptionDto.getUser()).isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        User user = userRepository.findById(subscriptionDto.getUser()).get();
        subscription.setUser(user);
        subscription.setStartDate(new Date());
        subscription.setPeriod(subscriptionDto.getPeriod());
        subscription.setStatus(subscriptionDto.getStatus());
        if (Objects.equals(subscription.getStatus(), "pending") || subscription.getPeriod() <= 0) {
            subscription.setEndDate(subscription.getStartDate());
            return subscription;
        }
        subscription.setEndDate(calculateEndDate(subscription.getStartDate(), subscription.getPeriod()));
        return subscription;
    }

    @Override
    public Date calculateEndDate(Date startDate, Integer period) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, period);
        return calendar.getTime();
    }

    @Override
    public Subscription getSubscription(Long id) {
        if (subscriptionRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Subscription not found");
        }
        Subscription subscription = subscriptionRepository.findById(id).get();
        if (Objects.equals(subscription.getStatus(), "cancelled")) {
            throw new ResourceNotFoundException("Subscription cancelled");
        }
        return subscriptionRepository.findById(id).get();
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription updateSubscription(Long id, SubscriptionRequestDto subscriptionDto) {
        Subscription subscription = getSubscription(id);
        subscription.setStartDate(subscriptionDto.getStartDate());
        subscription.setPeriod(subscriptionDto.getPeriod());
        subscription.setStatus(subscriptionDto.getStatus());
        subscription.setEndDate(calculateEndDate(subscription.getStartDate(), subscription.getPeriod()));
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Record rentBook(Long bookId, Long userId, Integer period) {
        Subscription subscription = subscriptionRepository.findByUser_Id(userId);
        if (subscription.getRemainingFee() > 0) {
            throw new RequestDeniedException("There is still remaining fee");
        }
        List<Record> records = recordRepository.findAllBySubscription_Id(subscription.getId());
        Book book = bookService.getBookById(bookId);
        if (book.getInStock() > 0 || records.size() >= subscription.getRentLimit()) {
            book.setInStock(book.getInStock() - 1);
            Record record = new Record();
            record.setBook(book);
            record.setRentDate(new Date());
            record.setExceedDate(calculateEndDate(record.getRentDate(), period));
            record.setSubscription(subscription);
            bookService.updateBook(bookId, new BookRequestDto(book));
            updateSubscription(subscription.getId(), new SubscriptionRequestDto(subscription));
            return recordRepository.save(record);
        }
        return null;
    }

    @Override
    public Subscription proceedReturnBook(Long bookId, Long userId) {
        Subscription subscription = subscriptionRepository.findByUser_Id(userId);
        List<Record> records = recordRepository.findAllBySubscription_Id(subscription.getId());
        for (Record record : records) {
            if (record.getBook().getId().equals(bookId)) {
                Calendar exceedCalendar = Calendar.getInstance();
                Calendar calendar = Calendar.getInstance();
                exceedCalendar.setTime(record.getExceedDate());
                calendar.setTime(new Date());
                recordRepository.delete(record);
                int monthsBetween = CalendarUtil.getMonthsBetween(calendar, exceedCalendar);
                if (monthsBetween <= 0) {
                    float fee = subscription.getLateFee() + subscription.getLateFeePercent() * monthsBetween;
                    subscription.setRemainingFee(fee);
                    return subscriptionRepository.save(subscription);
                }
                break;
            }
        }
        return null;
    }

    @Override
    public void completeReturnBook(Long invoiceId) {
        recordRepository.findById(invoiceId).ifPresent(invoice -> {
            Book book = invoice.getBook();
            book.setInStock(invoice.getBook().getInStock() - 1);
            bookRepository.save(book);
            invoice.setStatus("returned");
            recordRepository.save(invoice);
        });
    }

    @Override
    public Subscription completeRenewSubscription(Long userId) {
        Subscription subscription = subscriptionRepository.findByUser_Id(userId);
        subscription.setStatus("renewed");
        subscription.setEndDate(calculateEndDate(new Date(), subscription.getPeriod()));
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription completePayFee(Long userId) {
        Subscription subscription = subscriptionRepository.findByUser_Id(userId);
        subscription.setRemainingFee(0f);
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteSubscription(Long id) {
        Subscription subscription = getSubscription(id);
        subscription.setStatus("cancelled");
        subscriptionRepository.save(subscription);
    }
}
