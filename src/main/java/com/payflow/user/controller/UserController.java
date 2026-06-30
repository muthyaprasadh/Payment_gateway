package com.payflow.user.controller;

import com.payflow.user.dto.UserProfileResponse;
import com.payflow.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public UserProfileResponse getProfile() {
        return userService.getProfile();
    }
}