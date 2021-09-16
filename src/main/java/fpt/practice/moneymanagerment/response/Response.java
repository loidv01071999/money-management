package fpt.practice.moneymanagerment.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private String code;
    private String message;

    public Response(final ResponseMessage responseMessage, Object... params) {
        this.message = responseMessage.getMessage(params);
        this.code = responseMessage.getCode();
    }
}
