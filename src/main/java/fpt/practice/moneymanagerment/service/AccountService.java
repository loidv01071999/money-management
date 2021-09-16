package fpt.practice.moneymanagerment.service;

import fpt.practice.moneymanagerment.exception.BadRequestException;
import fpt.practice.moneymanagerment.exception.DataDuplicatedException;
import fpt.practice.moneymanagerment.request.RegisterRequest;

public interface AccountService {

    void registerAccount(RegisterRequest registerRequest) throws DataDuplicatedException, BadRequestException;
}
