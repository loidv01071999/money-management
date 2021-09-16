package fpt.practice.moneymanagerment.service;

import fpt.practice.moneymanagerment.request.AuthRequest;
import fpt.practice.moneymanagerment.response.TokenInfoResponse;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticateService {

    /**
     *
     * @param authRequest
     * @return
     * @throws AuthenticationException
     */
    TokenInfoResponse performAuthentication(AuthRequest authRequest) throws AuthenticationException;
}
