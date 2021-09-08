package fpt.practice.moneymanagerment.service.impl;

import fpt.practice.moneymanagerment.constant.StatusCode;
import fpt.practice.moneymanagerment.exception.RestApiException;
import fpt.practice.moneymanagerment.model.Account;
import fpt.practice.moneymanagerment.repository.AccountRepository;
import fpt.practice.moneymanagerment.request.RegisterRequest;
import fpt.practice.moneymanagerment.service.AccountService;
import fpt.practice.moneymanagerment.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void registerAccount(RegisterRequest registerRequest) {
        registerRequest = deletePlace(registerRequest);
        if (registerRequest.getUsername().isEmpty()){
            throw new RestApiException(StatusCode.USERNAME_EMPTY);
        }
        if(registerRequest.getPassword().isEmpty()){
            throw new RestApiException(StatusCode.PASSWORD_EMPTY);
        }
        if(registerRequest.getEmail().isEmpty()){
            throw new RestApiException(StatusCode.EMAIL_EMPTY);
        }
        if(!ValidateUtil.isPassword(registerRequest.getPassword())){
            throw new RestApiException(StatusCode.PASSWORD_WRONG_FORMAT);
        }
        if(!ValidateUtil.isEmail(registerRequest.getEmail())){
            throw new RestApiException(StatusCode.EMAIL_WRONG_FORMAT);
        }
        Account account = accountRepository.findAccountByUsername(registerRequest.getUsername());
        if(!Objects.isNull(account)){
            throw new RestApiException(StatusCode.USERNAME_EXISTED);
        }
        Account accountRegister = new Account();
        accountRegister.setUsername(registerRequest.getUsername());
        accountRegister.setPassword(registerRequest.getPassword());
        accountRegister.setEmail(registerRequest.getEmail());
        accountRegister.setBeginningBalance(registerRequest.getStartAmount());
        accountRepository.save(accountRegister);
    }

    @Override
    public Account login(String username, String password) {
        if(username.trim().isEmpty()){
            throw new RestApiException(StatusCode.USERNAME_EMPTY);
        }
        if(password.trim().isEmpty()){
            throw new RestApiException(StatusCode.PASSWORD_EMPTY);
        }
        Account account = accountRepository.findAccountByUsernameAndPassword(username,password);
        if(Objects.isNull(account)){
            throw new RestApiException(StatusCode.LOGIN_FAILED);
        }
        return account;
    }

    private RegisterRequest deletePlace(RegisterRequest registerRequest){
        RegisterRequest request = registerRequest.builder()
                .username(registerRequest.getUsername().trim())
                .password(registerRequest.getPassword().trim())
                .email(registerRequest.getEmail().trim())
                .startAmount(registerRequest.getStartAmount())
                .build();
        return request;
    }

}
