package com.example.spring_boot_auth.dtos;

import com.example.spring_boot_auth.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDto {
    private Long Id;
    private String nickName;
    private String firstName;
    private String lastName;
    private String email;
    private Collection<Role> roles;
}
