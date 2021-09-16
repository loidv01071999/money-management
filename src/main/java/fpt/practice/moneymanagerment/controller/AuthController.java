package fpt.practice.moneymanagerment.controller;

import fpt.practice.moneymanagerment.exception.UnauthorizedException;
import fpt.practice.moneymanagerment.request.AuthRequest;
import fpt.practice.moneymanagerment.response.ResponseMessage;
import fpt.practice.moneymanagerment.response.TokenInfoResponse;
import fpt.practice.moneymanagerment.service.AuthenticateService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private AuthenticateService authenticateService;

    @PostMapping("api/auth")
    public TokenInfoResponse authenticate(@Valid @RequestBody final AuthRequest authRequest) throws UnauthorizedException {
        try {
            LOG.info("Start to authenticate login request with information: " + authRequest);
            TokenInfoResponse tokenInfoResponse = authenticateService.performAuthentication(authRequest);
            LOG.info("End to authenticate login request with information: " + authRequest);
            return tokenInfoResponse;
        } catch (AuthenticationException e) {
            throw new UnauthorizedException(ResponseMessage.AuthenticateUserFailed);
        }
    }
}
