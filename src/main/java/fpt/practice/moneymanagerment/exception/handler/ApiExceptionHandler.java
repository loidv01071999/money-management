package fpt.practice.moneymanagerment.exception.handler;

import fpt.practice.moneymanagerment.exception.BadRequestException;
import fpt.practice.moneymanagerment.exception.ForbiddenException;
import fpt.practice.moneymanagerment.exception.InternalServerException;
import fpt.practice.moneymanagerment.exception.UnauthorizedException;
import fpt.practice.moneymanagerment.response.Response;
import fpt.practice.moneymanagerment.response.ResponseMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Class define return httpStatus when exception is thrown
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);

    /**
     * All the unhandled exception is handled here
     *
     * @return Response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response exceptionHandler(Exception e) {
        LOG.warn(e.getMessage());
        return new Response(ResponseMessage.DefaultInternalServerMessageError);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerException.class)
    @ResponseBody
    public String internalServerExceptionHandler(InternalServerException e) {
        LOG.warn(e.getMessage());
        return StringUtils.EMPTY;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public Response badRequestExceptionHandler(BadRequestException e) throws Exception {
        LOG.warn(e.getMessage());
        return new Response(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody
    public String forbiddenExceptionHandler(ForbiddenException e) {
        LOG.warn(e.getMessage());
        return StringUtils.EMPTY;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Response unauthorizedExceptionHandler(UnauthorizedException e) {
        LOG.warn(e.getMessage());
        return new Response(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) throws Exception {

        StringBuilder errorItemBuilder = new StringBuilder();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            String value = fieldError.getRejectedValue() == null ? "null" : fieldError.getRejectedValue().toString();

            errorItemBuilder.append(fieldError.getField()).append(", "+ value);
        }

        String errorCode = ResponseMessage.InvalidAccessError.getCode();
        String errorMessage = ResponseMessage.InvalidAccessError.getMessage(errorItemBuilder.toString());

        return new Response(errorCode, errorMessage);
    }
}
