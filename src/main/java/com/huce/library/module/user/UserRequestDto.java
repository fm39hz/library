package com.huce.library.module.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {
    private String username;
    private String password;
    private String role;
}
