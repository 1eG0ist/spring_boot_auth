package com.example.spring_boot_auth.service.Impl;

import com.example.spring_boot_auth.model.Role;
import com.example.spring_boot_auth.repository.RoleRepository;
import com.example.spring_boot_auth.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
