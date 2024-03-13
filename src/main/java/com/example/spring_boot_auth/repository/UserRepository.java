package com.example.spring_boot_auth.repository;

import com.example.spring_boot_auth.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    void deleteByEmail(String email);
    Optional<User> findUserByEmail(String email);
    Optional<User> findByNickName(String userNickName);
}
