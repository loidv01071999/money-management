package fpt.practice.moneymanagerment.service;

import fpt.practice.moneymanagerment.dto.SpendingDTO;
import fpt.practice.moneymanagerment.model.Spending;
import fpt.practice.moneymanagerment.request.SpendingRequest;

import java.util.List;

public interface SpendingService {

    List<SpendingDTO> getListSpendings();

    void addSpending(SpendingRequest spendingRequest);

    void updateSpending(Long spendingId, SpendingRequest spendingRequest);

    void removeSpending(Long spendingId);
}
