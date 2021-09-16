package fpt.practice.moneymanagerment.service.impl;

import fpt.practice.moneymanagerment.constant.Constants;
import fpt.practice.moneymanagerment.exception.BadRequestException;
import fpt.practice.moneymanagerment.exception.DataDuplicatedException;
import fpt.practice.moneymanagerment.model.Account;
import fpt.practice.moneymanagerment.repository.AccountRepository;
import fpt.practice.moneymanagerment.request.RegisterRequest;
import fpt.practice.moneymanagerment.response.ResponseMessage;
import fpt.practice.moneymanagerment.service.AccountService;
import fpt.practice.moneymanagerment.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerAccount(RegisterRequest registerRequest) throws DataDuplicatedException, BadRequestException {
        registerRequest = deletePlace(registerRequest);
        if (!ValidateUtil.isPassword(registerRequest.getPassword())) {
            throw new BadRequestException(ResponseMessage.PasswordWrongFormat);
        }
        if (!ValidateUtil.isEmail(registerRequest.getEmail())) {
            throw new BadRequestException(ResponseMessage.EmailWrongFormat);
        }
        Account account = accountRepository.findAccountByUsername(registerRequest.getUsername());
        if (!Objects.isNull(account)) {
            throw new DataDuplicatedException();
        }
        Account accountRegister = new Account();
        accountRegister.setUsername(registerRequest.getUsername());
        accountRegister.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        accountRegister.setEmail(registerRequest.getEmail());
        accountRegister.setBeginningBalance(registerRequest.getStartAmount());
        accountRegister.setStatus(Constants.STATUS_ACTIVE);
        accountRegister.setRole(Constants.ROLE_ADMIN);
        accountRepository.save(accountRegister);
    }


    private RegisterRequest deletePlace(RegisterRequest registerRequest) {
        RegisterRequest request = registerRequest.builder()
                .username(registerRequest.getUsername().trim())
                .password(registerRequest.getPassword().trim())
                .email(registerRequest.getEmail().trim())
                .startAmount(registerRequest.getStartAmount())
                .build();
        return request;
    }

}
