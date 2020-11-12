package com.example.test.controllers;

import com.example.test.model.BankAndClientViews;
import com.example.test.model.Deposit;
import com.example.test.repo.DepositRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@RestController
@RequestMapping("deposits")
public class DepositController {

    private final DepositRepo depositRepo;
    @Autowired
    public DepositController(DepositRepo depositRepo) {
        this.depositRepo = depositRepo;
    }

    @GetMapping
    @JsonView(BankAndClientViews.ForUser.class)
    public List<Deposit> getAll() {
//        List<Deposit> deposits = depositRepo.findAll();
//        return deposits;
        return depositRepo.findAll();
    }

    @PostMapping
    public Deposit create(@RequestBody Deposit deposit) {
        return depositRepo.save(deposit);
    }

    @PutMapping("{id}")
    public Deposit update(
            @PathVariable("id") Deposit depositFromDb,
            @RequestBody Deposit deposit
    ) {
        BeanUtils.copyProperties(deposit, depositFromDb, "id");
        return depositRepo.save(depositFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Deposit deposit) {
        depositRepo.delete(deposit);
    }

}
