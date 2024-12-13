package com.huce.library.modules.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class InvoiceRequestDto {
    private Long bookId;
    private Integer period;
}
