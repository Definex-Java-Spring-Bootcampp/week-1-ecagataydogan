import enums.Category;

import model.Customer;
import model.Order;
import model.OrderedProduct;
import model.Product;
import onlineshopping.manager.OnlineShoppingManager;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main2 {
    public static void main(String[] args) {
        OnlineShoppingManager onlineShoppingManager = new OnlineShoppingManager();
        List<Customer> customers = Arrays.asList(
                new Customer("Cem", 26),
                new Customer("Cem", 28),
                new Customer("Deniz", 32),
                new Customer("Kaan", 44),
                new Customer("Cem", 15)
        );

        customers.forEach(onlineShoppingManager::addCustomer);

        List<Product> products = Arrays.asList(
                new Product("Iphone11", BigDecimal.valueOf(24502), Category.TECHNOLOGY),
                new Product("Iphone12", BigDecimal.valueOf(35632), Category.TECHNOLOGY),
                new Product("Macbook Pro", BigDecimal.valueOf(56321), Category.TECHNOLOGY),
                new Product("Tooth Brush", BigDecimal.valueOf(89.20), Category.MEDICINE),
                new Product("Vitamin B", BigDecimal.valueOf(349.99), Category.MEDICINE),
                new Product("Jean", BigDecimal.valueOf(1543.88), Category.CLOTHING),
                new Product("T-shirt", BigDecimal.valueOf(590), Category.CLOTHING)
        );

        Random rand = new Random();
        for (Customer customer : customers) {
            List<OrderedProduct> orderedProducts = new ArrayList<>();
            for (int i = 0; i < rand.nextInt(1, 5); i++) {
                Product product = products.get(rand.nextInt(products.size()));
                BigDecimal quantity = BigDecimal.valueOf(rand.nextInt(1, 4));
                orderedProducts.add(new OrderedProduct(customer, product, quantity));
            }

            Order order = new Order(customer, orderedProducts);
            onlineShoppingManager.addOrder(order, customer);
        }

        System.out.println(onlineShoppingManager.getTotalCustomerCount());
        System.out.println(onlineShoppingManager.getProductCountByCustomerName("Kaan"));
        System.out.println(onlineShoppingManager.listBillsOverAmount(BigDecimal.valueOf(130000)));
        System.out.println(onlineShoppingManager.calculateTotalShoppingAmountForSpecificCustomers());
    }
}
