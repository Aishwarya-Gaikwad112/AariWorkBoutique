package com.boutique.backend.controller;


import com.boutique.backend.model.User;
import com.boutique.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Allow frontend calls
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signUp(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/signin")
    public boolean signIn(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }
}
