package fpt.practice.moneymanagerment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Setter
@Getter
public class SpendingDTO {
    private Long spendingId;
    private String spendingTypeName;
    private String subSpendingTypeName;
    private String description;
    private String spendingDate;
    private String amount;
}
