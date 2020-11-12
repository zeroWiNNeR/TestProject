package com.example.test.repo;

import com.example.test.model.Bank;
import com.example.test.model.Client;
import com.example.test.model.Deposit;
import com.example.test.model.OrganizationalAndLegalForm;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class DepositRepoTest {

    @Autowired
    private DepositRepo depositRepo;

    @Test
    void shouldSaveDeposit() {
        Bank bank = new Bank("Sberbank", "111111111");
        Client client = new Client("Ivan", "Ivan Ivanov", "Perm", OrganizationalAndLegalForm.IP);
        Deposit deposit = new Deposit(client, bank, null, (short) 3, (short) 0);

        Deposit savedDeposit = depositRepo.save(deposit);

        Assert.assertNotNull(savedDeposit);
        Assert.assertNotNull(savedDeposit.getId());
    }

}