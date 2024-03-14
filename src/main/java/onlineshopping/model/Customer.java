package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private int age;
    private List<Order> orders;
    private List<Bill> bills;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
        this.orders = new ArrayList<>();
        this.bills = new ArrayList<>();
    }

    public void addOrder(Order order) {
        bills.add(new Bill(this,order));
        orders.add(order);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
