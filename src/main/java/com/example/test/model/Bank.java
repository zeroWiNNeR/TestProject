package com.example.test.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@Entity
@Table(name = "BANKS")
public class Bank {

    @Id
    @SequenceGenerator(name="banks_generator", sequenceName = "banks_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="banks_generator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "bank_identification_code")
    @Length(min = 9, max = 9, message = "БИК должен состоять из 9 цифр!")
    private String bankIdentificationCode;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "bank")
    private Deposit deposit;


    public Bank() {
    }

    public Bank(String name, String bankIdentificationCode) {
        this.name = name;
        this.bankIdentificationCode = bankIdentificationCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankIdentificationCode() {
        return bankIdentificationCode;
    }

    public void setBankIdentificationCode(String bankIdentificationCode) {
        this.bankIdentificationCode = bankIdentificationCode;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }
}
