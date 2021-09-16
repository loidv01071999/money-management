package fpt.practice.moneymanagerment.request;

import lombok.Data;

@Data
public class UserRequest{
    private String email;
    private String role;
    private String status;
}
