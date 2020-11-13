package com.example.test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "К депозиту должен быть привязан клиент!")
    private Client client;

    @JsonView(BankAndClientViews.ForUser.class)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id")
    @NotNull(message = "К депозиту должен быть привязан банк!")
    private Bank bank;

    @JsonView(BankAndClientViews.ForUser.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "open_date")
    private LocalDateTime openDate;

    @JsonView(BankAndClientViews.ForUser.class)
    @Column(name = "percent")
    @Min(value = 0, message = "Процент не может быть меньше 0%!")
    private Short percent;

    @JsonView(BankAndClientViews.ForUser.class)
    @Column(name = "time_in_months_since_opening")
    @Min(value = 1, message = "Срок открытия не может быть меньше 1 месяца!")
    private Short openTimeInMonths;


    public Deposit() {
    }

    public Deposit(Client client, Bank bank, LocalDateTime openDate, Short percent, Short openTimeInMonths) {
        this.client = client;
        this.bank = bank;
        this.openDate = openDate;
        this.percent = percent;
        this.openTimeInMonths = openTimeInMonths;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Short getPercent() {
        return percent;
    }

    public void setPercent(Short percent) {
        this.percent = percent;
    }

    public Short getOpenTimeInMonths() {
        return openTimeInMonths;
    }

    public void setOpenTimeInMonths(Short openTimeInMonths) {
        this.openTimeInMonths = openTimeInMonths;
    }

}
