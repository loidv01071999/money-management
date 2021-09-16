package fpt.practice.moneymanagerment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class SpendingResponse {
    private Long spendingId;
    private String spendingTypeName;
    private String subSpendingTypeName;
    private String description;
    private String spendingDate;
    private String amount;
}
