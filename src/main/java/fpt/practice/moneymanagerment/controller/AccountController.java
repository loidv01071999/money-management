package fpt.practice.moneymanagerment.controller;

import fpt.practice.moneymanagerment.exception.BadRequestException;
import fpt.practice.moneymanagerment.exception.DataDuplicatedException;
import fpt.practice.moneymanagerment.request.RegisterRequest;
import fpt.practice.moneymanagerment.response.Response;
import fpt.practice.moneymanagerment.response.ResponseMessage;
import fpt.practice.moneymanagerment.service.AccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = -1)
@RequestMapping("/api")
public class AccountController {

    private static final Logger logger = Logger.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping("/account")
    public Response registerAccount(@RequestBody RegisterRequest registerRequest) throws BadRequestException{
        try {
            logger.debug("Register account: ");
            accountService.registerAccount(registerRequest);
            return new Response(ResponseMessage.RegisterUserSuccessfully);
        }catch (DataDuplicatedException e){
            throw new BadRequestException(ResponseMessage.DuplicatedUsername, registerRequest.getUsername());
        }
    }

}
