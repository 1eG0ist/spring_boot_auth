package com.example.spring_boot_auth.dtos;

import lombok.Data;

@Data
public class RegistrationUserDto {
    private String nickName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
