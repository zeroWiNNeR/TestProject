package com.example.test.controller;

import com.example.test.model.BankAndClientViews;
import com.example.test.model.Deposit;
import com.example.test.service.DepositService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@RestController
@RequestMapping("deposits")
@EnableWebMvc
public class DepositController {

    private final DepositService depositService;
    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping
    @JsonView(BankAndClientViews.ForUser.class)
    public List<Deposit> getAll() {
        return depositService.findAll();
    }

    @PostMapping()
    @JsonView(BankAndClientViews.ForUser.class)
    public Deposit create(@RequestBody Deposit deposit) {
        return depositService.saveDeposit(deposit);
    }

    @PutMapping("{id}")
    @JsonView(BankAndClientViews.ForUser.class)
    public Deposit update(
            @PathVariable("id") Deposit depositFromDb,
            @RequestBody Deposit deposit
    ) {
        return depositService.updateDeposit(depositFromDb, deposit);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Deposit deposit) {
        depositService.deleteDeposit(deposit);
    }

}
