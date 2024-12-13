package com.huce.library.module.invoice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/")
    public ResponseEntity<List<InvoiceResponseDto>> getAllInvoice(){
        List<InvoiceResponseDto> invoices = new ArrayList<>();
        for (Invoice invoice : invoiceService.getAllInvoice()) {
            invoices.add(new InvoiceResponseDto(invoice));
        }
        return ResponseEntity.ok().body(invoices);
    }

    @GetMapping("/subscription/{subscriptionId}")
    public ResponseEntity<List<InvoiceResponseDto>> getAllInvoiceWithSubscription(@PathVariable Long subscriptionId){
        List<InvoiceResponseDto> invoices = new ArrayList<>();
        for (Invoice invoice : invoiceService.getAllInvoiceBySubscriptionId(subscriptionId)) {
            invoices.add(new InvoiceResponseDto(invoice));
        }
        return ResponseEntity.ok().body(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDto> getInvoiceById(@PathVariable Long id){
        return ResponseEntity.ok().body(new InvoiceResponseDto(invoiceService.getInvoiceById(id)));
    }
}
