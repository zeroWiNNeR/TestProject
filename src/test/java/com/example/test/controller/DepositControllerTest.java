package com.example.test.controller;

import com.example.test.model.Bank;
import com.example.test.model.Client;
import com.example.test.model.Deposit;
import com.example.test.model.OrganizationalAndLegalForm;
import com.example.test.service.DepositService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
@ActiveProfiles("test")
class DepositControllerTest {

//    private static final ObjectMapper mapper = new ObjectMapper();
//    @Autowired
//    private MockMvc mockMvc;

    @InjectMocks
    private DepositController depositController;

    @Mock
    private DepositService depositService;

    @Before
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldFindAllDeposits() {
        List<Deposit> deposits = new ArrayList<>();
        Bank firstBank = new Bank("Sberbank", "111111111");
        Client client = new Client("Ivan", "Ivan Ivanov", "Perm", OrganizationalAndLegalForm.IP);
        Deposit firstDeposit = new Deposit(client, firstBank, null, (short) 3, (short) 0);
        firstBank.setId(1L);
        deposits.add(firstDeposit);
        Bank secondBank = new Bank("Klukva", "222222222");
        Deposit secondDeposit = new Deposit(client, secondBank, null, (short) 3, (short) 0);
        secondDeposit.setId(2L);
        deposits.add(secondDeposit);
        Mockito.when(depositService.findAll()).thenReturn(deposits);

        List<Deposit> depositsFromDb = depositController.getAll();

        Assert.assertNotNull(depositsFromDb);
        Assert.assertEquals(2, depositsFromDb.size());
        Mockito.verify(depositService, Mockito.times(1)).findAll();
    }

    @Test
    void shouldFindFilteredAndSortedDeposits() {
        List<Deposit> deposits = new ArrayList<>();
        Client client = new Client("Ivan", "Ivan Ivanov", "Perm", OrganizationalAndLegalForm.IP);
        Bank firstBank = new Bank("Klukva", "222222222");
        Deposit firstDeposit = new Deposit(client, firstBank, LocalDateTime.parse("2020-11-11T10:11:30"), (short) 3, (short) 5);
        firstDeposit.setId(2L);
        Bank secondBank = new Bank("Klukva", "222222222");
        Deposit secondDeposit = new Deposit(client, secondBank, LocalDateTime.parse("2020-11-11T01:35:34"), (short) 3, (short) 4);
        secondDeposit.setId(3L);
        deposits.add(secondDeposit);
        Mockito.when(depositService.getFilteredByOpenTimeAndSortedByTarget((short) 3, "openDate")).thenReturn(deposits);

        List<Deposit> depositsFromDb = depositController.getFilteredAndSorted((short) 3, "openDate");

        Assert.assertFalse(depositsFromDb.isEmpty());
        Assert.assertEquals(1, depositsFromDb.size());
        Mockito.verify(depositService, Mockito.times(1))
                .getFilteredByOpenTimeAndSortedByTarget((short) 3, "openDate");
    }

    @Test
    void shouldSaveDeposit() {
        Bank bank = new Bank("Sberbank", "111111111");
        Client client = new Client("Ivan", "Ivan Ivanov", "Perm", OrganizationalAndLegalForm.IP);
        Deposit deposit = new Deposit(client, bank, null, (short) 3, (short) 0);

//        String json = mapper.writeValueAsString(deposit);
//        this.mockMvc.perform(post("/deposits").contentType(MediaType.APPLICATION_JSON).content(json))
//                .andDo(print())
//                .andExpect(status().isOk());

        Deposit returnedDeposit = deposit;
        returnedDeposit.setId(1L);

        Mockito.when(depositService.saveDeposit(deposit)).thenReturn(returnedDeposit);

        Deposit savedDeposit = depositController.create(deposit);

        Assert.assertNotNull(savedDeposit);
        Assert.assertNotNull(savedDeposit.getId());
        Mockito.verify(depositService, Mockito.times(1)).saveDeposit(deposit);
    }

    @Test
    void shouldSaveChangesOfDeposit() {
        Bank bank = new Bank("Sberbank", "222222222");
        Client client = new Client("Sasha", "Sasha Ivanov", "Perm", OrganizationalAndLegalForm.IP);
        Deposit deposit = new Deposit(client, bank, null, (short) 3, (short) 1);
        deposit.setId(1L);

        Mockito.when(depositService.updateDeposit(any(Deposit.class), any(Deposit.class))).thenReturn(deposit);

        Bank bankFromDb = new Bank("Sberbank", "111111111");
        Client clientFromDb = new Client("Ivan", "Ivan Ivanov", "Perm", OrganizationalAndLegalForm.IP);
        Deposit depositFromDb = new Deposit(clientFromDb, bankFromDb, null, (short) 3, (short) 0);

        Bank bankUpdate = new Bank("Sberbank", "222222222");
        Client clientUpdate = new Client("Sasha", "Sasha Ivanov", "Perm", OrganizationalAndLegalForm.IP);
        Deposit depositUpdate = new Deposit(clientUpdate, bankUpdate, null, (short) 3, (short) 1);

        Deposit updatedDeposit = depositController.update(depositFromDb, depositUpdate);

        Assert.assertNotNull(updatedDeposit);
        Assert.assertNotNull(updatedDeposit.getId());
        Mockito.verify(depositService, Mockito.times(1)).updateDeposit(any(Deposit.class), any(Deposit.class));
    }

    @Test
    void shouldDeleteDeposit() {
        Client firstClient = new Client("Ivan", "Ivan Ivanov", "Perm", OrganizationalAndLegalForm.IP);
        Bank firstBank = new Bank("Sberbank", "111111111");
        Deposit firstDeposit = new Deposit(firstClient, firstBank, null, (short) 3, (short) 0);
        firstBank.setId(1L);

        depositController.delete(firstDeposit);

        Mockito.verify(depositService, Mockito.times(1)).deleteDeposit(any(Deposit.class));
    }
}