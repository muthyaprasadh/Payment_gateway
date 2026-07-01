package com.payflow.transaction.service;

import com.payflow.transaction.dto.TransactionResponse;

import java.util.List;

public interface TransactionService {

    List<TransactionResponse> getTransactionHistory();

}