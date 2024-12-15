package com.huce.library.module.record;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;

    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<Record> getAllInvoice() {
        return recordRepository.findAll();
    }

    @Override
    public List<Record> getAllInvoiceBySubscriptionId(Long subscriptionId) {
        return recordRepository.findAllBySubscription_Id(subscriptionId);
    }

    @Override
    public Record getInvoiceById(Long id) {
        if (recordRepository.findById(id).isEmpty()) {
            return null;
        }
        return recordRepository.findById(id).get();
    }
}
