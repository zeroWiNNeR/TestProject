package com.example.test.service.impl;

import com.example.test.model.Bank;
import com.example.test.repo.BankRepo;
import com.example.test.service.BankService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@Service
public class BankServiceImpl implements BankService {

    private final BankRepo bankRepo;
    @Autowired
    public BankServiceImpl(BankRepo bankRepo) {
        this.bankRepo = bankRepo;
    }

    @Override
    public List<Bank> findAll() {
        return bankRepo.findAll();
    }

    @Override
    public Bank saveBank(Bank bank) {
        return bankRepo.save(bank);
    }

    @Override
    public Bank updateBank(Bank bankFromDb, Bank bank) {
        BeanUtils.copyProperties(bank, bankFromDb, "id", "deposit");
        return bankRepo.save(bank);
    }

    @Override
    public void deleteBank(Bank bank) {
        bankRepo.delete(bank);
    }
}
