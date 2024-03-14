package manager;

import model.Bill;
import model.Customer;
import model.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OnlineShoppingManager {
    private List<Customer> customers;
    private List<Order> orders;
    private List<Bill> bills;

    public OnlineShoppingManager() {
        this.customers = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.bills = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void addOrder(Order order,Customer customer) {
        for (Customer updatedCustomer : customers) {
            //id check == reference checking
            if (customer == updatedCustomer) {
                Bill bill = new Bill(customer,order);
                updatedCustomer.addOrder(order);
                orders.add(order);
                bills.add(bill);
            }

        }
    }
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    public int getTotalCustomerCount() {
        return customers.size();
    }

    public long getProductCountByCustomerName(String name) {
        return orders.stream()
                .filter(order -> name.equals(order.getCustomer().getName()))
                .flatMapToLong(order -> order.getProducts().stream()
                        .mapToLong(orderedProduct -> orderedProduct.getCount().longValue()))
                .sum();

    }


    public List<Bill> listBillsOverAmount(BigDecimal amount) {
        return bills.stream()
                .filter(bill -> bill.getAmount().compareTo(amount) > 0)
                .collect(Collectors.toList());
    }

    public  BigDecimal calculateTotalShoppingAmountForSpecificCustomers() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Order order : orders) {
            Customer customer = order.getCustomer();
            if ("Cem".equals(customer.getName()) && customer.getAge() > 25 && customer.getAge() < 30) {
                totalAmount = totalAmount.add(order.getBill().getAmount());
            }
        }
        return totalAmount;
    }


}
