package com.example.spring_boot_auth.controller;


import com.example.spring_boot_auth.config.MyUserDetails;
import com.example.spring_boot_auth.model.User;
import com.example.spring_boot_auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor // for userService auto delegate life cycle to spring
public class UserController {

    private final UserService userService;

    @PostMapping("save_user")
    public String saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{email}")
    public MyUserDetails findByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }

    @PutMapping("update_user")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("delete_user/{email}")
    public String deleteUser(@PathVariable("email") String email) {
        return userService.deleteUser(email);
    }

}

