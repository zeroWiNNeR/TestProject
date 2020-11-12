package com.example.test.service;

import com.example.test.model.Deposit;

import java.util.List;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
public interface DepositService {

    /**
     * Возвращает депозиты из БД со связными клиентами и банками
     */
    List<Deposit> findAll();

    /**
     * Сохраняет в БД новый депозит
     */
    Deposit saveDeposit(Deposit deposit);

    /**
     * Сохраняет изменение данных по депозиту в БД
     */
    Deposit updateDeposit(Deposit depositFromDb, Deposit deposit);

    /**
     * Удаляет депозит из БД
     */
    void deleteDeposit(Deposit deposit);

}
