package fpt.practice.moneymanagerment.exception;

import fpt.practice.moneymanagerment.response.ResponseMessage;
import lombok.Getter;

@Getter
public class BadRequestException extends Exception {
    private String code;

    public BadRequestException(final ResponseMessage responseMessage, Object... params) {
        super(responseMessage.getMessage(params));
        this.code = responseMessage.getCode();
    }
}
