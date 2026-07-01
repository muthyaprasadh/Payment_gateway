package com.payflow.admin.service;

import com.payflow.admin.dto.AdminDashboardResponse;
import com.payflow.admin.dto.UserResponse;
import com.payflow.admin.dto.WalletAdminResponse;
import com.payflow.transaction.dto.TransactionResponse;

import java.util.List;

public interface AdminService {

    List<UserResponse> getAllUsers();

    List<WalletAdminResponse> getAllWallets();

    List<TransactionResponse> getAllTransactions();

    AdminDashboardResponse getDashboard();

}