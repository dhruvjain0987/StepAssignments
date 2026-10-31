package PracticeProblems.PPWeek8;
import java.util.*;

interface Week8P5IPaymentMethod {
    boolean pay(double amount);
    String getPaymentName();
}

class Week8P5CreditCardPayment implements Week8P5IPaymentMethod {
    public boolean pay(double amount) {
        return true;
    }

    public String getPaymentName() {
        return "Credit Card";
    }
}

class Week8P5DigitalWalletPayment implements Week8P5IPaymentMethod {
    public boolean pay(double amount) {
        return false;
    }

    public String getPaymentName() {
        return "Digital Wallet";
    }
}

class Week8P5FoodItem {
    String name;
    double price;

    Week8P5FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Week8P5LineItem {
    Week8P5FoodItem item;
    int quantity;

    Week8P5LineItem(Week8P5FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    double getTotal() {
        return item.price * quantity;
    }
}

class Week8P5Customer {
    String name;

    Week8P5Customer(String name) {
        this.name = name;
    }

    void notifyCustomer(String message) {
        System.out.println("Notification: " + message);
    }
}

class Week8P5FoodOrder {
    static int counter = 122;
    int orderId;
    Week8P5Customer customer;
    ArrayList<Week8P5LineItem> items = new ArrayList<>();
    String status = "Pending Payment";
    boolean placed = false;

    Week8P5FoodOrder(Week8P5Customer customer) {
        this.customer = customer;
        orderId = ++counter;
        System.out.println("Order created.");
    }

    void addItem(Week8P5FoodItem item, int quantity) {
        if (placed || quantity <= 0) return;

        items.add(new Week8P5LineItem(item, quantity));

        System.out.println("Added " + item.name +
            " (Qty " + quantity + ")");
    }

    double getTotal() {
        double total = 0;

        for (Week8P5LineItem item : items)
            total += item.getTotal();

        return total;
    }

    void placeOrder(Week8P5IPaymentMethod payment) {
        if (items.isEmpty()) {
            System.out.println(
                "Cannot place order: Order must contain at least one item.");
            return;
        }

        placed = true;
        System.out.println("Order placed successfully.");

        boolean success = payment.pay(getTotal());

        if (success) {
            status = "Paid";
            System.out.println("Payment via " +
                payment.getPaymentName() + " successful.");
            System.out.println("Order status: " + status);

            customer.notifyCustomer("Order #" + orderId +
                " placed and paid.");
        } else {
            System.out.println("Payment via " +
                payment.getPaymentName() + " failed.");
            System.out.println("Order status: " + status);

            customer.notifyCustomer("Order #" + orderId +
                " placed, awaiting payment.");
        }
    }
}

public class Prob5 {
    public static void main(String[] args) {
        Week8P5Customer customer =
            new Week8P5Customer("Dhruv");

        Week8P5FoodItem pizza =
            new Week8P5FoodItem("Pizza", 200);

        Week8P5FoodItem soda =
            new Week8P5FoodItem("Soda", 50);

        Week8P5FoodItem burger =
            new Week8P5FoodItem("Burger", 150);

        Week8P5FoodOrder order1 =
            new Week8P5FoodOrder(customer);

        order1.placeOrder(new Week8P5CreditCardPayment());

        order1.addItem(pizza, 2);
        order1.addItem(soda, 1);

        order1.placeOrder(new Week8P5CreditCardPayment());

        Week8P5FoodOrder order2 =
            new Week8P5FoodOrder(customer);

        order2.addItem(burger, 1);
        order2.placeOrder(new Week8P5DigitalWalletPayment());
    }
}