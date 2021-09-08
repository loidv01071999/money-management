package fpt.practice.moneymanagerment.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private Double startAmount;
}
