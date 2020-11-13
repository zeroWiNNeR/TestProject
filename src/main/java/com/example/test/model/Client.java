package com.example.test.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@Entity
@Table(name = "CLIENTS")
public class Client {

    @Id
    @SequenceGenerator(name="clients_generator", sequenceName = "clients_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="clients_generator")
    @JsonView(BankAndClientViews.ForUser.class)
    private Long id;

    @JsonView(BankAndClientViews.ForUser.class)
    @Column(name = "name")
    @NotEmpty(message = "Нельзя сохранить клиента без имени!")
    private String name;

    @JsonView(BankAndClientViews.ForUser.class)
    @Column(name = "short_name")
    private String shortName;

    @JsonView(BankAndClientViews.ForUser.class)
    @Column(name = "address")
    @NotEmpty(message = "Нельзя сохранить клиента без адреса!")
    private String address;

    @JsonView(BankAndClientViews.ForUser.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "organizational_form")
    private OrganizationalAndLegalForm organizationalAndLegalForm;

    @JsonView(BankAndClientViews.All.class)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "client")
    private Deposit deposit;


    public Client() {
    }

    public Client(String name, String shortName, String address, OrganizationalAndLegalForm organizationalAndLegalForm) {
        this.name = name;
        this.shortName = shortName;
        this.address = address;
        this.organizationalAndLegalForm = organizationalAndLegalForm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrganizationalAndLegalForm getOrganizationalAndLegalForm() {
        return organizationalAndLegalForm;
    }

    public void setOrganizationalAndLegalForm(OrganizationalAndLegalForm organizationalAndLegalForm) {
        this.organizationalAndLegalForm = organizationalAndLegalForm;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

}
