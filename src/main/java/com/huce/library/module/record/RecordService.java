package com.huce.library.module.record;

import java.util.List;

public interface RecordService {
    List<Record> getAllInvoice();

    List<Record> getAllInvoiceBySubscriptionId(Long subscriptionId);

    Record getInvoiceById(Long id);
}
