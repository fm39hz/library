package com.huce.library.module.invoice;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> getAllInvoiceBySubscriptionId(Long subscriptionId) {
        return invoiceRepository.findAllBySubscription_Id(subscriptionId);
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        if (invoiceRepository.findById(id).isEmpty()) {
            return null;
        }
        return invoiceRepository.findById(id).get();
    }
}
