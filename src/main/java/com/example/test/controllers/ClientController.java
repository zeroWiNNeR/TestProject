package com.example.test.controllers;

import com.example.test.model.BankAndClientViews;
import com.example.test.model.Client;
import com.example.test.repo.ClientRepo;
import com.fasterxml.jackson.annotation.JsonView;
import javafx.collections.FXCollections;
import javafx.embed.swt.FXCanvas;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientRepo clientRepo;
    @Autowired
    public ClientController(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @GetMapping
    @JsonView(BankAndClientViews.ForUser.class)
    public List<Client> getAll() {
        return clientRepo.findAll();
    }

    @PostMapping
    @JsonView(BankAndClientViews.ForUser.class)
    public Client create(@RequestBody Client client) {
        return clientRepo.save(client);
    }

    @PutMapping("{id}")
    @JsonView(BankAndClientViews.ForUser.class)
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
