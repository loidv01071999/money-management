package fpt.practice.moneymanagerment.exception;

import fpt.practice.moneymanagerment.response.ResponseMessage;
import lombok.Getter;

@Getter
public class UnauthorizedException extends Exception {
    private String code;

    public UnauthorizedException(final ResponseMessage responseMessage, Object... params) {
        super(responseMessage.getMessage(params));
        this.code = responseMessage.getCode();
    }
}
