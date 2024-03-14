package model;

import java.math.BigDecimal;
import java.util.List;

public class Bill {
    private BigDecimal amount;
    private Customer customer;
    private Order order;

    public Bill(Customer customer, Order order) {
        this.customer = customer;
        this.order = order;
        this.amount = calculateAmount(order);
    }

    public BigDecimal calculateAmount(Order order) {
        BigDecimal amount = BigDecimal.ZERO;
        List<OrderedProduct> orderedProducts = order.getProducts();
        for (OrderedProduct orderedProduct : orderedProducts) {
            BigDecimal productTotal = orderedProduct.getProduct().getPrice().multiply(orderedProduct.getCount());
            amount = amount.add(productTotal);
        }
        return amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
