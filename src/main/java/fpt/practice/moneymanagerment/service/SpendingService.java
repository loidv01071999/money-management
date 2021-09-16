package fpt.practice.moneymanagerment.service;

import fpt.practice.moneymanagerment.response.SpendingResponse;
import fpt.practice.moneymanagerment.exception.BadRequestException;
import fpt.practice.moneymanagerment.request.SpendingRequest;

import java.util.List;

public interface SpendingService {

    List<SpendingResponse> getListSpendings();

    void addSpending(SpendingRequest spendingRequest) throws BadRequestException;

    void updateSpending(Long spendingId, SpendingRequest spendingRequest) throws BadRequestException;

    void removeSpending(Long spendingId) throws BadRequestException;
}
