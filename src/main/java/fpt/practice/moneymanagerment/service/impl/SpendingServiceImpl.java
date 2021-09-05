package fpt.practice.moneymanagerment.service.impl;

import fpt.practice.moneymanagerment.constant.StatusCode;
import fpt.practice.moneymanagerment.dto.SpendingDTO;
import fpt.practice.moneymanagerment.exception.RestApiException;
import fpt.practice.moneymanagerment.model.Account;
import fpt.practice.moneymanagerment.model.Spending;
import fpt.practice.moneymanagerment.model.SubSpendingType;
import fpt.practice.moneymanagerment.model.Unit;
import fpt.practice.moneymanagerment.repository.AccountRepository;
import fpt.practice.moneymanagerment.repository.SpendingRepository;
import fpt.practice.moneymanagerment.repository.SubSpendingTypeRepository;
import fpt.practice.moneymanagerment.repository.UnitRepository;
import fpt.practice.moneymanagerment.request.SpendingRequest;
import fpt.practice.moneymanagerment.service.SpendingService;
import fpt.practice.moneymanagerment.util.DateUtil;
import fpt.practice.moneymanagerment.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SpendingServiceImpl implements SpendingService {

    @Autowired
    private SpendingRepository spendingRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private SubSpendingTypeRepository subSpendingTypeRepository;

    private static final DateUtil DATE_UTIL = new DateUtil();

    @Override
    public List<SpendingDTO> getListSpendings() {
        return spendingDTOS(spendingRepository.findAll());
    }

    @Override
    public void addSpending(SpendingRequest spendingRequest) {
        Spending spending = new Spending();
        try {
            Account account = accountRepository.getById(spendingRequest.getAccountId());
            spending.setAccount(account);
        }catch (Exception e){
            throw new RestApiException(StatusCode.ACCOUNT_NOT_EXIST);
        }

        try {
            SubSpendingType subSpendingType = subSpendingTypeRepository.getById(spendingRequest.getSubSpendingTypeId());
            spending.setSubSpendingType(subSpendingType);
        }catch (Exception e){
            throw new RestApiException(StatusCode.SUB_SPENDING_TYPE_NOT_EXIST);
        }

        try {
            Unit unit = unitRepository.getById(spendingRequest.getUnitId());
            spending.setUnit(unit);
        }catch (Exception e){
            throw new RestApiException(StatusCode.UNIT_NOT_EXIST);
        }
        spending.setAmount(spendingRequest.getAmount());
        spending.setDescription(spendingRequest.getDescription());
        spending.setDate(DATE_UTIL.getCurrentDate());
        spendingRepository.save(spending);
    }

    @Override
    public void updateSpending(Long spendingId, SpendingRequest spendingRequest) {
        Spending spending = null;
        try {
            spending = spendingRepository.getById(spendingId);
        }catch (Exception e){
            throw new RestApiException(StatusCode.SPENDING_NOT_EXIST);
        }

        try {
            Account account = accountRepository.getById(spendingRequest.getAccountId());
            spending.setAccount(account);
        }catch (Exception e){
            throw new RestApiException(StatusCode.ACCOUNT_NOT_EXIST);
        }

        try {
            SubSpendingType subSpendingType = subSpendingTypeRepository.getById(spendingRequest.getSubSpendingTypeId());
            spending.setSubSpendingType(subSpendingType);
        }catch (Exception e){
            throw new RestApiException(StatusCode.SUB_SPENDING_TYPE_NOT_EXIST);
        }

        try {
            Unit unit = unitRepository.getById(spendingRequest.getUnitId());
            spending.setUnit(unit);
        }catch (Exception e){
            throw new RestApiException(StatusCode.UNIT_NOT_EXIST);
        }
        spending.setAmount(spendingRequest.getAmount());
        spending.setDescription(spendingRequest.getDescription());
        spendingRepository.save(spending);
    }

    @Override
    public void removeSpending(Long spendingId) {
        Spending spending = null;
        try {
            spending = spendingRepository.getById(spendingId);
        }catch (Exception e){
            throw new RestApiException(StatusCode.SPENDING_NOT_EXIST);
        }
        spendingRepository.delete(spending);
    }

    private List<SpendingDTO> spendingDTOS(List<Spending> spendings) {
        List<SpendingDTO> listSpendingDTOs = new ArrayList<>();
        DateUtil dateUtil = new DateUtil();
        NumberUtil numberUtil = new NumberUtil();
        for (Spending spending :
                spendings) {
            SpendingDTO spendingDTO = SpendingDTO.builder().spendingId(spending.getId())
                    .spendingDate(dateUtil.convertDate(spending.getDate()))
                    .spendingTypeName(spending.getSubSpendingType().getSpendingType().getName())
                    .subSpendingTypeName(spending.getSubSpendingType().getName())
                    .amount(numberUtil.convertAmount(spending.getAmount()))
                    .description(spending.getDescription())
                    .build();
            listSpendingDTOs.add(spendingDTO);
        }
        return listSpendingDTOs;
    }
}
