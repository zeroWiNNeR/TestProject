package com.example.test.controllers;

import com.example.test.model.Client;
import com.example.test.repo.ClientRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientRepo clientRepo;
    @Autowired
    public ClientController(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @GetMapping
    public List<Client> getAll() {
        return clientRepo.findAll();
    }

    @PostMapping
    public Client create(@RequestBody Client client) {
        return clientRepo.save(client);
    }

    @PutMapping("{id}")
    public Client update(
            @PathVariable("id") Client clientFromDb,
            @RequestBody Client client
    ) {
        BeanUtils.copyProperties(client, clientFromDb, "id");
        return clientRepo.save(clientFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Client client) {
        clientRepo.delete(client);
    }

}
