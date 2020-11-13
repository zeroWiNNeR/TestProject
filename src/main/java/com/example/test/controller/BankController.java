package com.example.test.controller;

import com.example.test.model.Bank;
import com.example.test.model.BankAndClientViews;
import com.example.test.service.BankService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@RestController
@RequestMapping("banks")
public class BankController {

    private final BankService bankService;
    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    @JsonView(BankAndClientViews.ForUser.class)
    public List<Bank> getAll() {
        return bankService.findAll();
    }

    @PostMapping
    @JsonView(BankAndClientViews.ForUser.class)
    public Bank create(@RequestBody @Valid Bank bank) {
        return bankService.saveBank(bank);
    }

    @PutMapping("{id}")
    @JsonView(BankAndClientViews.ForUser.class)
    public Bank update(
            @PathVariable("id") Bank bankFromDb,
            @RequestBody @Valid Bank bank
    ) {
        return bankService.updateBank(bankFromDb, bank);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Bank bank) {
        bankService.deleteBank(bank);
    }

}
