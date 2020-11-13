package com.example.test.service.impl;

import com.example.test.model.Deposit;
import com.example.test.repo.DepositRepo;
import com.example.test.service.DepositService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@Service
public class DepositServiceImpl implements DepositService {

    private final DepositRepo depositRepo;
    @Autowired
    public DepositServiceImpl(DepositRepo depositRepo) {
        this.depositRepo = depositRepo;
    }

    @Override
    public List<Deposit> findAll() {
        return depositRepo.findAll();
    }

    @Override
    public List<Deposit> getFilteredByOpenTimeAndSortedByTarget(Short openTimeInMonths, String sortTarget) {
        if ((sortTarget == null || "".equals(sortTarget)) && (openTimeInMonths == null)) {
            return depositRepo.findAll();
        } else if ((sortTarget == null || "".equals(sortTarget)) && openTimeInMonths > -1) {
            return depositRepo.getDepositsFilteredByOpenTime(openTimeInMonths);
        } else if ((sortTarget != null && sortTarget.length()>0) && (openTimeInMonths == null || openTimeInMonths == 0)) {
            return depositRepo.getDepositsSortedByTarget(Sort.by(sortTarget));
        } else {
            return depositRepo.getDepositsFilteredByOpenMonthsAndSortedByTarget(openTimeInMonths, Sort.by(sortTarget));
        }
    }

    @Override
    public Deposit saveDeposit(Deposit deposit) {
        deposit.setOpenDate(LocalDateTime.now());
        return depositRepo.save(deposit);
    }

    @Override
    public Deposit updateDeposit(Deposit depositFromDb, Deposit deposit) {
        BeanUtils.copyProperties(deposit, depositFromDb, "id", "openDate");
        return depositRepo.save(depositFromDb);
    }

    @Override
    public void deleteDeposit(Deposit deposit) {
        depositRepo.delete(deposit);
    }

}
