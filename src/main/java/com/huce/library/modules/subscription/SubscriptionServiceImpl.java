package com.huce.library.modules.subscription;

import com.huce.library.exception.ResourceNotFoundException;
import com.huce.library.modules.book.Book;
import com.huce.library.modules.book.BookDto;
import com.huce.library.modules.book.BookService;
import com.huce.library.modules.invoice.InvoiceRepository;
import com.huce.library.modules.user.User;
import com.huce.library.modules.user.UserRepository;
import org.springframework.stereotype.Service;
import com.huce.library.modules.invoice.Invoice;

import java.util.*;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final InvoiceRepository invoiceRepository;
    private final BookService bookService;
    private final UserRepository userRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, InvoiceRepository invoiceRepository, BookService bookService, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.invoiceRepository = invoiceRepository;
        this.bookService = bookService;
        this.userRepository = userRepository;
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
        if (userRepository.findById(userId).isEmpty()) {
            return null;
        }
        User user = userRepository.findById(userId).get();
        Book book = bookService.getBookById(bookId);
        Subscription subscription = user.getSubscription();
        List<Invoice> invoices = invoiceRepository.findAllBySubscription_Id(subscription.getId());
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
    public void deleteSubscription(Long id) {
        Subscription subscription = getSubscription(id);
        subscription.setStatus("cancelled");
    }
}
