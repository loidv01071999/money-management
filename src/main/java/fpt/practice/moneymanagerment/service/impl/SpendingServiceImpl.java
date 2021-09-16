package fpt.practice.moneymanagerment.service.impl;

import fpt.practice.moneymanagerment.dto.SpendingDTO;
import fpt.practice.moneymanagerment.exception.BadRequestException;
import fpt.practice.moneymanagerment.model.Account;
import fpt.practice.moneymanagerment.model.Spending;
import fpt.practice.moneymanagerment.model.SubSpendingType;
import fpt.practice.moneymanagerment.model.Unit;
import fpt.practice.moneymanagerment.repository.AccountRepository;
import fpt.practice.moneymanagerment.repository.SpendingRepository;
import fpt.practice.moneymanagerment.repository.SubSpendingTypeRepository;
import fpt.practice.moneymanagerment.repository.UnitRepository;
import fpt.practice.moneymanagerment.request.SpendingRequest;
import fpt.practice.moneymanagerment.response.ResponseMessage;
import fpt.practice.moneymanagerment.service.SpendingService;
import fpt.practice.moneymanagerment.utils.DateUtil;
import fpt.practice.moneymanagerment.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void addSpending(SpendingRequest spendingRequest) throws BadRequestException {
        Spending spending = new Spending();

        spending = checkNotFound(spendingRequest,spending);

        spending.setAmount(spendingRequest.getAmount());
        spending.setDescription(spendingRequest.getDescription());
        spending.setDate(DATE_UTIL.getCurrentDate());
        spendingRepository.save(spending);
    }

    @Override
    public void updateSpending(Long spendingId, SpendingRequest spendingRequest) throws BadRequestException{
        Optional<Spending> spendingOptional = spendingRepository.findById(spendingId);
        if(!spendingOptional.isPresent()){
            throw new BadRequestException(ResponseMessage.SpendingIdNotFound);
        }
        Spending spending = spendingOptional.get();
        spending = checkNotFound(spendingRequest,spending);
        spending.setAmount(spendingRequest.getAmount());
        spending.setDescription(spendingRequest.getDescription());
        spendingRepository.save(spending);
    }

    @Override
    public void removeSpending(Long spendingId) throws BadRequestException{
        Optional<Spending> spendingOptional = spendingRepository.findById(spendingId);
        if(!spendingOptional.isPresent()){
            throw new BadRequestException(ResponseMessage.SpendingIdNotFound);
        }
        Spending spending = spendingOptional.get();
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

    private Spending checkNotFound(SpendingRequest spendingRequest, Spending spending) throws BadRequestException{
        Optional<Account> account = accountRepository.findById(spendingRequest.getAccountId());
        if (!account.isPresent()) {
            throw new BadRequestException(ResponseMessage.AccountIdNotFound);
        }
        spending.setAccount(account.get());
        //check sub spending type
        Optional<SubSpendingType> subSpendingType = subSpendingTypeRepository.findById(spendingRequest.getSubSpendingTypeId());
        if(!subSpendingType.isPresent()){
            throw new BadRequestException(ResponseMessage.SubTypeSpendingIdNotFound);
        }
        spending.setSubSpendingType(subSpendingType.get());
        //check unit
        Optional<Unit> unit = unitRepository.findById(spendingRequest.getUnitId());
        if(!unit.isPresent()){
            throw new BadRequestException(ResponseMessage.UnitIdNotFound);
        }
        spending.setUnit(unit.get());

        return spending;
    }
}
