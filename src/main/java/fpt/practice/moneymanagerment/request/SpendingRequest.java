package fpt.practice.moneymanagerment.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SpendingRequest {
    private Long accountId;
    private Long subSpendingTypeId;
    private String description;
    private Double amount;
    private Long unitId;
}
