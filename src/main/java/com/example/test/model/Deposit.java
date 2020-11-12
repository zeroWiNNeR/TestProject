package com.example.test.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@Entity
@Table(name = "DEPOSITS")
public class Deposit {

    @Id
    @SequenceGenerator(name="deposits_generator", sequenceName = "deposits_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="deposits_generator")
    @JsonView(BankAndClientViews.ForUser.class)
    private Long id;

    @JsonView(BankAndClientViews.ForUser.class)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonView(BankAndClientViews.ForUser.class)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @JsonView(BankAndClientViews.ForUser.class)
    @Column(name = "open_date")
    private LocalDateTime openDate;

    @JsonView(BankAndClientViews.ForUser.class)
    @Column(name = "percent")
    private Byte percent;

    @JsonView(BankAndClientViews.ForUser.class)
    @Column(name = "time_in_months_since_opening")
    private Short timeInMonthsSinceOpening;


    public Deposit() {
    }

    public Deposit(Client client, Bank bank, LocalDateTime openDate, Byte percent, Short timeInMonthsSinceOpening) {
        this.client = client;
        this.bank = bank;
        this.openDate = openDate;
        this.percent = percent;
        this.timeInMonthsSinceOpening = timeInMonthsSinceOpening;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDateTime openDate) {
        this.openDate = openDate;
    }

    public Byte getPercent() {
        return percent;
    }

    public void setPercent(Byte percent) {
        this.percent = percent;
    }

    public Short getTimeInMonthsSinceOpening() {
        return timeInMonthsSinceOpening;
    }

    public void setTimeInMonthsSinceOpening(Short timeInMonthsSinceOpening) {
        this.timeInMonthsSinceOpening = timeInMonthsSinceOpening;
    }
}
