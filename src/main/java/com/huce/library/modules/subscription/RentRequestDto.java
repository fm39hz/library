package com.huce.library.modules.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RentRequestDto {
    private Long bookId;
    private Integer period;
}
