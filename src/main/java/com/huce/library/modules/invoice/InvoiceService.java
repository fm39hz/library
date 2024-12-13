package com.huce.library.modules.invoice;

import java.util.List;

public interface InvoiceService {
    List<Invoice> getAllInvoice();
    List<Invoice> getAllInvoiceBySubscriptionId(Long subscriptionId);
    Invoice getInvoiceById(Long id);
}
