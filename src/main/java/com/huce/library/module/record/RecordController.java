package com.huce.library.module.record;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {
    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/")
    public ResponseEntity<List<RecordResponseDto>> getAllInvoice() {
        List<RecordResponseDto> invoices = new ArrayList<>();
        for (Record record : recordService.getAllInvoice()) {
            invoices.add(new RecordResponseDto(record));
        }
        return ResponseEntity.ok().body(invoices);
    }

    @GetMapping("/subscription/{subscriptionId}")
    public ResponseEntity<List<RecordResponseDto>> getAllInvoiceWithSubscription(@PathVariable Long subscriptionId) {
        List<RecordResponseDto> invoices = new ArrayList<>();
        for (Record record : recordService.getAllInvoiceBySubscriptionId(subscriptionId)) {
            invoices.add(new RecordResponseDto(record));
        }
        return ResponseEntity.ok().body(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecordResponseDto> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new RecordResponseDto(recordService.getInvoiceById(id)));
    }
}
