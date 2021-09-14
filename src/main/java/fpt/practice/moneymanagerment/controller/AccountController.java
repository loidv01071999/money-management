package fpt.practice.moneymanagerment.controller;

import fpt.practice.moneymanagerment.model.Account;
import fpt.practice.moneymanagerment.request.RegisterRequest;
import fpt.practice.moneymanagerment.service.AccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = -1)
@RequestMapping("/api")
public class AccountController {

    private static final Logger logger = Logger.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<?> registerAccount(@RequestBody RegisterRequest registerRequest){
        logger.debug("Register account: ");
        accountService.registerAccount(registerRequest);
        ResponseEntity<String> response = new ResponseEntity<>("Register successfully", HttpStatus.CREATED);
        return response;
    }

    @RequestMapping("/account")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password){
        logger.debug("Login: \nusername: " + username + "password: " + password + "\n");
        Account account = accountService.login(username, password);
        ResponseEntity<Account> response = new ResponseEntity<>(account, HttpStatus.CREATED);
        return response;
    }
}
