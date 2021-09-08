package fpt.practice.moneymanagerment.service;

import fpt.practice.moneymanagerment.model.Account;
import fpt.practice.moneymanagerment.request.RegisterRequest;

public interface AccountService {

    void registerAccount(RegisterRequest registerRequest);

    Account login(String username, String password);
}
