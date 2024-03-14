package model;

import java.util.List;

public class Order {
    private Customer customer;
    private List<OrderedProduct> products;
    private Bill bill;

    public Order(Customer customer, List<OrderedProduct> products) {
        this.customer = customer;
        this.products = products;
        this.bill = new Bill(customer,this);
    }

    public List<OrderedProduct> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Bill getBill() {
        return bill;
    }
}
