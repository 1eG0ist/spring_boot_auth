package com.example.spring_boot_auth.service;

import com.example.spring_boot_auth.config.MyUserDetails;
import com.example.spring_boot_auth.dtos.RegistrationUserDto;
import com.example.spring_boot_auth.model.User;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public interface UserService extends UserDetailsService {

    String saveUser(User user);

    MyUserDetails findByEmail(String email);

    User updateUser(User user);

    @Transactional
    String deleteUser(String email);

    @Override
    MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    User createNewUser(RegistrationUserDto registrationUserDto);

}
