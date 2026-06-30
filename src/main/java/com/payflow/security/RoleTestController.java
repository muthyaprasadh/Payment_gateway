package com.payflow.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleTestController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String user() {
        return "Welcome USER";
    }

    @GetMapping("/merchant")
    @PreAuthorize("hasRole('MERCHANT')")
    public String merchant() {
        return "Welcome MERCHANT";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Welcome ADMIN";
    }
}