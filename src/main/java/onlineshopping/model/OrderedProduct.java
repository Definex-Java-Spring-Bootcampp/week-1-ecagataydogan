package model;

import java.math.BigDecimal;

public class OrderedProduct {
    private Product product;
    private BigDecimal count;
    private Customer customer;

    public OrderedProduct(Customer customer, Product product, BigDecimal count) {
        this.customer = customer;
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getCount() {
        return count;
    }
}
