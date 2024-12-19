package com.huce.library.module.invoice;

import com.huce.library.module.subscription.Subscription;

import java.util.List;

public interface InvoiceService {
    List<Invoice> findAllInvoice();
    Invoice findInvoiceById(Long id);
    Invoice createInvoice(Subscription subscription, Float amount, String description);
}
