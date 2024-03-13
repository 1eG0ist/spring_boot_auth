package com.example.spring_boot_auth.service;

import com.example.spring_boot_auth.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getUserRole();
}
