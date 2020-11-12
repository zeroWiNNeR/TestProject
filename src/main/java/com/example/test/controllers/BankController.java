package com.example.test.controllers;

import com.example.test.model.Bank;
import com.example.test.repo.BankRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@RestController
@RequestMapping("bank")
public class BankController {

    private final BankRepo bankRepo;
    @Autowired
    public BankController(BankRepo bankRepo) {
        this.bankRepo = bankRepo;
    }

    @GetMapping
    public List<Bank> getAll() {
        return bankRepo.findAll();
    }

    @PostMapping
    public Bank create(@RequestBody Bank bank) {
        return bankRepo.save(bank);
    }

    @PutMapping("{id}")
    public Bank update(
            @PathVariable("id") Bank bankFromDb,
            @RequestBody Bank bank
    ) {
        BeanUtils.copyProperties(bank, bankFromDb, "id");
        return bankRepo.save(bankFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Bank bank) {
        bankRepo.delete(bank);
    }

}
