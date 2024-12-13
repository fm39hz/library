package com.huce.library.module.subscription;

import com.huce.library.exception.RequestDeniedException;
import com.huce.library.exception.ResourceNotFoundException;
import com.huce.library.module.book.Book;
import com.huce.library.module.book.BookDto;
import com.huce.library.module.book.BookRepository;
import com.huce.library.module.book.BookService;
import com.huce.library.module.invoice.Invoice;
import com.huce.library.module.invoice.InvoiceRepository;
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
    private final InvoiceRepository invoiceRepository;
    private final BookService bookService;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, InvoiceRepository invoiceRepository, BookService bookService, UserRepository userRepository, BookRepository bookRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.invoiceRepository = invoiceRepository;
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
        user.setSubscription(subscription);
        userRepository.save(user);
        if (subscription.getPeriod() == null || Objects.equals(subscription.getStatus(), "pending")) {
            subscription.setEndDate(subscription.getStartDate());
            subscription.setPeriod(-1);
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
    public Subscription updateSubscription(Long id, SubscriptionRequestDto subscriptionDto) {
        Subscription subscription = getSubscription(id);
        subscription.setStartDate(subscriptionDto.getStartDate());
        subscription.setPeriod(subscriptionDto.getPeriod());
        subscription.setStatus(subscriptionDto.getStatus());
        subscription.setEndDate(calculateEndDate(subscription.getStartDate(), subscription.getPeriod()));
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Invoice rentBook(Long bookId, Long userId, Integer period) {
        Subscription subscription = subscriptionRepository.findByUser_Id(userId);
        if (subscription.getRemainingFee() > 0) {
            throw new RequestDeniedException("There is still remaining fee");
        }
        List<Invoice> invoices = invoiceRepository.findAllBySubscription_Id(subscription.getId());
        Book book = bookService.getBookById(bookId);
        if (book.getInStock() > 0 || invoices.size() >= subscription.getRentLimit()) {
            book.setInStock(book.getInStock() - 1);
            Invoice invoice = new Invoice();
            invoice.setBook(book);
            invoice.setRentDate(new Date());
            invoice.setExceedDate(calculateEndDate(invoice.getRentDate(), period));
            invoice.setSubscription(subscription);
            bookService.updateBook(bookId, new BookDto(book));
            updateSubscription(subscription.getId(), new SubscriptionRequestDto(subscription));
            return invoiceRepository.save(invoice);
        }
        return null;
    }

    @Override
    public Subscription proceedReturnBook(Long bookId, Long userId) {
        Subscription subscription = subscriptionRepository.findByUser_Id(userId);
        List<Invoice> invoices = invoiceRepository.findAllBySubscription_Id(subscription.getId());
        for (Invoice invoice : invoices) {
            if (invoice.getBook().getId().equals(bookId)) {
                Book book = invoice.getBook();
                book.setInStock(invoice.getBook().getInStock() - 1);
                bookRepository.save(book);
                Calendar exceedCalendar = Calendar.getInstance();
                Calendar calendar = Calendar.getInstance();
                exceedCalendar.setTime(invoice.getExceedDate());
                calendar.setTime(new Date());
                invoiceRepository.delete(invoice);
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
        invoiceRepository.deleteById(invoiceId);
    }

    @Override
    public void deleteSubscription(Long id) {
        Subscription subscription = getSubscription(id);
        subscription.setStatus("cancelled");
    }
}
