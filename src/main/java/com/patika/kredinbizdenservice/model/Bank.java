package com.patika.kredinbizdenservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bank {

    private static Bank instance;
    private String name;
    private List<Loan> loanList;
    private List<CreditCard> creditCards;
    private List<User> users;

    private Bank(String name) {
        this.name = name;
        this.loanList = new ArrayList<>();
        this.creditCards = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public static synchronized Bank getInstance(String name) {
        if (instance == null) {
            instance = new Bank(name);
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Application> getAllApplications() {
        return users.stream()
                .flatMap(user -> user.getApplicationList().stream())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", loanList=" + loanList +
                '}';
    }
}
