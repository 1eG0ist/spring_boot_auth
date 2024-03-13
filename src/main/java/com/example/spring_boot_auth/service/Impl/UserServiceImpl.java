package com.example.spring_boot_auth.service.Impl;

import com.example.spring_boot_auth.config.MyUserDetails;
import com.example.spring_boot_auth.dtos.RegistrationUserDto;
import com.example.spring_boot_auth.model.User;
import com.example.spring_boot_auth.repository.UserRepository;
import com.example.spring_boot_auth.service.RoleService;
import com.example.spring_boot_auth.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public String saveUser(User user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of(roleService.getUserRole()));
            userRepository.save(user);
            return "User added!";
        } catch (Exception e) {
            return "Something went wrong";
        }
    }

    public MyUserDetails findByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.map(MyUserDetails::new)
                .orElse(null);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public String deleteUser(String email) {
        try {
            userRepository.deleteByEmail(email);
            return "User deleted";
        } catch (Exception e) {
            return "Something went wrong!";
        }
    }

    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }

    public User createNewUser(RegistrationUserDto registrationUserDto) {
        User user = new User();
        user.setNickName(registrationUserDto.getNickName());
        user.setEmail(registrationUserDto.getEmail());
        user.setFirstName(registrationUserDto.getFirstName());
        user.setLastName(registrationUserDto.getLastName());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setRoles(List.of(roleService.getUserRole()));
        return userRepository.save(user);
    }
}
