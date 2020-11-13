package com.example.test.controller;

import com.example.test.model.BankAndClientViews;
import com.example.test.model.Client;
import com.example.test.service.ClientService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @JsonView(BankAndClientViews.ForUser.class)
    public List<Client> getAll() {
        return clientService.findAll();
    }

    @PostMapping
    @JsonView(BankAndClientViews.ForUser.class)
    public Client create(@RequestBody @Valid Client client) {
        return clientService.saveClient(client);
    }

    @PutMapping("{id}")
    @JsonView(BankAndClientViews.ForUser.class)
    public Client update(
            @PathVariable("id") Client clientFromDb,
            @RequestBody @Valid Client client
    ) {
        return clientService.updateClient(clientFromDb, client);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Client client) {
        clientService.deleteClient(client);
    }

}
