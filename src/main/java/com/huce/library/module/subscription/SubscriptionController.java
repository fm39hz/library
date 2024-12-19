package com.huce.library.module.subscription;

import com.huce.library.module.invoice.Invoice;
import com.huce.library.module.invoice.InvoiceService;
import com.huce.library.module.jwt.UserId;
import com.huce.library.module.payment.*;
import com.huce.library.module.record.Record;
import com.huce.library.module.record.RecordRequestDto;
import com.huce.library.module.record.RecordResponseDto;
import com.huce.library.module.user.CustomUserDetails;
import com.huce.library.module.user.Roles;
import com.huce.library.module.user.User;
import com.huce.library.module.user.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    private final UserService userService;
    private final PaymentService paymentService;
    private final SubscriptionService subscriptionService;
    private final InvoiceService invoiceService;
    @Value("${vnp.feUrl}")
    private String frontendUrl;

    public SubscriptionController(SubscriptionService subscriptionService, UserService userService, InvoiceService invoiceService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
        this.invoiceService = invoiceService;
        this.paymentService = PaymentServiceFactory.getService(PaymentMethods.VNPay);
    }

    @PostMapping("/")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<SubscriptionResponseDto> create(@RequestBody SubscriptionRequestDto subscriptionDto) {
        return ResponseEntity.ok().body(new SubscriptionResponseDto(subscriptionService.createSubscription(subscriptionDto)));
    }

    @PutMapping("/")
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@UserId Long userId, @RequestBody SubscriptionRequestDto subscriptionDto) {
        User user = ((CustomUserDetails) userService.loadUserById(userId)).getUser();
        Subscription result = subscriptionService.updateSubscription(user.getSubscription().getId(), subscriptionDto);
        return ResponseEntity.ok().body(new SubscriptionResponseDto(result));
    }

    @GetMapping("/")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@UserId Long userId) {
        CustomUserDetails user = (CustomUserDetails) userService.loadUserById(userId);
        return ResponseEntity.ok().body(new SubscriptionResponseDto(user.getUser().getSubscription()));
    }

    @GetMapping("/get-all")
    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscription() {
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        List<SubscriptionResponseDto> subscriptionResponseDtoList = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            subscriptionResponseDtoList.add(new SubscriptionResponseDto(subscription));
        }
        return ResponseEntity.ok().body(subscriptionResponseDtoList);
    }

    @DeleteMapping("/")
    @RolesAllowed(Roles.USER)
    public ResponseEntity<SubscriptionResponseDto> cancelSubscription(@UserId Long userId) {
        CustomUserDetails user = (CustomUserDetails) userService.loadUserById(userId);
        subscriptionService.deleteSubscription(user.getUser().getSubscription().getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/rent")
    public ResponseEntity<RecordResponseDto> rentBook(@UserId Long userId, @RequestBody RecordRequestDto recordRequestDto) {
        CustomUserDetails user = (CustomUserDetails) userService.loadUserById(userId);
        Record record = subscriptionService.rentBook(recordRequestDto.getBookId(), user.getUser().getId(), recordRequestDto.getPeriod());
        if (record == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new RecordResponseDto(record));
    }

    @PostMapping("/return-book/{bookId}")
    public ResponseEntity<SubscriptionResponseDto> returnBook(@UserId Long userId, @PathVariable Long bookId) {
        CustomUserDetails user = (CustomUserDetails) userService.loadUserById(userId);
        return ResponseEntity.ok().body(new SubscriptionResponseDto(subscriptionService.proceedReturnBook(bookId, user.getUser().getId())));
    }

    @GetMapping("/pay-remaining-fee")
    public ResponseEntity<PaymentResponseDto> payRemainingFee(@UserId Long userId) throws UnsupportedEncodingException {
        CustomUserDetails user = (CustomUserDetails) userService.loadUserById(userId);
        Subscription subscription = user.getUser().getSubscription();
        if (subscription.getRemainingFee() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String paymentDetail = user.getUsername() + " pay remaining fee";
        Invoice savedInvoice = invoiceService.createInvoice(subscription, subscription.getRemainingFee(), paymentDetail);
        return ResponseEntity.ok().body(paymentService.createPaymentUrl(savedInvoice.getId(), subscription.getRemainingFee(), paymentDetail.replaceAll(" ", "+")));
    }

    @GetMapping("/renew")
    public ResponseEntity<PaymentResponseDto> renewSubscription(@UserId Long userId) throws UnsupportedEncodingException {
        CustomUserDetails user = (CustomUserDetails) userService.loadUserById(userId);
        Subscription subscription = user.getUser().getSubscription();
        if (subscription.getEndDate().compareTo(new Date()) > 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String paymentDetail = user.getUsername() + " renew subscription";
        Invoice savedInvoice = invoiceService.createInvoice(subscription, 200000f, paymentDetail);
        return ResponseEntity.ok().body(paymentService.createPaymentUrl(savedInvoice.getId(), 200000, paymentDetail.replaceAll(" ", "+")));
    }

    @GetMapping("/success")
    public ResponseEntity<Void> success(@RequestParam Map<String, String> fields) {
        Invoice invoice = paymentService.processPayment(fields);
        String url;
        if (!Objects.equals(invoice.getStatus(), PaymentStatus.SUCCESS)) {
            url = frontendUrl + "/error";
        } else {
            url = String.format(frontendUrl + "/success?id=%d&status=%s&amount=%f&description=%s", invoice.getId(), invoice.getStatus(), invoice.getAmount(), invoice.getDescription());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/complete-renew")
    public ResponseEntity<SubscriptionResponseDto> completeRenew(@UserId Long userId) {
        return ResponseEntity.ok().body(new SubscriptionResponseDto(subscriptionService.completeRenewSubscription(userId)));
    }

    @GetMapping("/complete-pay-fee")
    public ResponseEntity<SubscriptionResponseDto> completePayFee(@UserId Long userId) {
        return ResponseEntity.ok().body(new SubscriptionResponseDto(subscriptionService.completePayFee(userId)));
    }
}
