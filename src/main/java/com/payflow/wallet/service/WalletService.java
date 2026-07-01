package com.payflow.wallet.service;

import com.payflow.wallet.dto.DepositRequest;
import com.payflow.wallet.dto.DepositResponse;
import com.payflow.wallet.dto.TransferRequest;
import com.payflow.wallet.dto.TransferResponse;
import com.payflow.wallet.dto.WalletResponse;
import com.payflow.wallet.dto.WithdrawRequest;
import com.payflow.wallet.dto.WithdrawResponse;

public interface WalletService {

    WalletResponse getWallet();

    DepositResponse deposit(DepositRequest request);

    WithdrawResponse withdraw(WithdrawRequest request);
    TransferResponse transfer(TransferRequest request);

}