package com.huce.library.module.record;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RecordRequestDto {
    private Long bookId;
    private Integer period;
}
