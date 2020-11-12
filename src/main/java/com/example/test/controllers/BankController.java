package com.example.test.controllers;

import com.example.test.model.Bank;
import com.example.test.model.BankAndClientViews;
import com.example.test.model.Client;
import com.example.test.repo.BankRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@RestController
@RequestMapping("banks")
public class BankController {

    private final BankRepo bankRepo;
    @Autowired
    public BankController(BankRepo bankRepo) {
        this.bankRepo = bankRepo;
    }

    @GetMapping
    @JsonView(BankAndClientViews.ForUser.class)
    public List<Bank> getAll() {
        return bankRepo.findAll();
    }

    @PostMapping
    @JsonView(BankAndClientViews.ForUser.class)
    public Bank create(@RequestBody Bank bank) {
        return bankRepo.save(bank);
    }

    @PutMapping("{id}")
    @JsonView(BankAndClientViews.ForUser.class)
    public Bank update(
            @PathVariable("id") Bank bankFromDb,
            @RequestBody Bank bank
    ) {
        BeanUtils.copyProperties(bank, bankFromDb, "id", "deposit");
        return bankRepo.save(bankFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Bank bank) {
        bankRepo.delete(bank);
    }

}
