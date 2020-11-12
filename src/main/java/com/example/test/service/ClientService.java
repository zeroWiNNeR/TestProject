package com.example.test.service;

import com.example.test.model.Client;

import java.util.List;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
public interface ClientService {

    /**
     * Возвращает весь список клиентов из БД
     */
    List<Client> findAll();

    /**
     * Сохраняет в БД нового клиента
     */
    Client saveClient(Client client);

    /**
     * Сохраняет изменение данных по депозиту в БД
     */
    Client updateClient(Client clientFromDb, Client client);

    /**
     * Удаляет депозит из БД
     */
    void deleteClient(Client client);
}
