package com.example.test.service.impl;

import com.example.test.model.Client;
import com.example.test.repo.ClientRepo;
import com.example.test.service.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;
    @Autowired
    public ClientServiceImpl(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public List<Client> findAll() {
        return clientRepo.findAll();
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public Client updateClient(Client clientFromDb, Client client) {
        BeanUtils.copyProperties(client, clientFromDb, "id", "deposit");
        return clientRepo.save(client);
    }

    @Override
    public void deleteClient(Client client) {
        clientRepo.delete(client);
    }

}
