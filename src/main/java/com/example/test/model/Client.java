package com.example.test.model;

import javax.persistence.*;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@Entity
@Table(name = "CLIENTS")
public class Client {

    @Id
    @SequenceGenerator(name="clients_generator", sequenceName = "clients_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="clients_generator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "address")
    private String address;

    @Column(name = "organizational_form")
    private OrganizationalAndLegalForm organizationalAndLegalForm;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "client")
    private Deposit deposit;


    public Client() {
    }

    public Client(String name, String shortName, String address, OrganizationalAndLegalForm organizationalAndLegalForm) {
        this.name = name;
        this.shortName = shortName;
        this.address = address;
        this.organizationalAndLegalForm = organizationalAndLegalForm;
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
