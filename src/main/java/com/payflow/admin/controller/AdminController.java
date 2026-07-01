package com.payflow.admin.controller;

import com.payflow.admin.dto.AdminDashboardResponse;
import com.payflow.admin.dto.UserResponse;
import com.payflow.admin.dto.WalletAdminResponse;
import com.payflow.admin.service.AdminService;
import com.payflow.transaction.dto.TransactionResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/wallets")
    public List<WalletAdminResponse> getWallets() {
        return adminService.getAllWallets();
    }

    @GetMapping("/transactions")
    public List<TransactionResponse> getTransactions() {
        return adminService.getAllTransactions();
    }

    @GetMapping("/dashboard")
    public AdminDashboardResponse getDashboard() {
        return adminService.getDashboard();
    }
}