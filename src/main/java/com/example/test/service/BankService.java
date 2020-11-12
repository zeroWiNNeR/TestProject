package com.example.test.service;

import com.example.test.model.Bank;

import java.util.List;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
public interface BankService {

    /**
     * Возвращает банки из БД
     */
    List<Bank> findAll();

    /**
     * Сохраняет в БД новый банк
     */
    Bank saveBank(Bank bank);

    /**
     * Сохраняет изменение данных по банку в БД
     */
    Bank updateBank(Bank bankFromDb, Bank bank);

    /**
     * Удаляет банк из БД
     */
    void deleteBank(Bank bank);

}
