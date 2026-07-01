package com.payflow.user.controller;

import com.payflow.user.dto.ChangePasswordRequest;
import com.payflow.user.dto.MessageResponse;
import com.payflow.user.dto.UpdateProfileRequest;
import com.payflow.user.dto.UserProfileResponse;
import com.payflow.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/profile")
    public UserProfileResponse updateProfile(
            @Valid @RequestBody UpdateProfileRequest request) {

        return userService.updateProfile(request);
    }

    @PutMapping("/change-password")
    public MessageResponse changePassword(
            @Valid @RequestBody ChangePasswordRequest request) {

        return userService.changePassword(request);
    }
}