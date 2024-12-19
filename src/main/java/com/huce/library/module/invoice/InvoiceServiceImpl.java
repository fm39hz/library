package com.huce.library.module.invoice;

import com.huce.library.module.subscription.Subscription;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findInvoiceById(Long id) {
        if (invoiceRepository.findById(id).isEmpty()) {
            return null;
        }
        return invoiceRepository.findById(id).get();
    }

    @Override
    public Invoice createInvoice(Subscription subscription, Float amount, String description) {
        Invoice invoice = new Invoice();
        invoice.setAmount(amount);
        invoice.setDescription(description);
        invoice.setSubscription(subscription);
        invoice.setCreateTime(new Date());
        return invoiceRepository.save(invoice);
    }
}
