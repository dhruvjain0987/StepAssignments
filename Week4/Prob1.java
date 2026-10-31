package Week4;

public class Prob1 {
    public static void main(String[] args) {
        String[][] orders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        Week4P1FoodOrder.processBatch(orders);

        Week4P1FoodOrder order =
            new Week4P1FoodOrder("Ravi", "Paneer");

        order.markDelivered();
        order.markDelivered();
    }
}

class Week4P1FoodOrder {
    private String studentName;
    private String dishName;
    private boolean delivered;

    public Week4P1FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty() ||
            dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid order");
        }

        this.studentName = studentName;
        this.dishName = dishName;
        this.delivered = false;
    }

    void markDelivered() {
        if (!delivered) {
            delivered = true;
            System.out.println("Order delivered successfully");
        } else {
            System.out.println("Order already delivered");
        }
    }

    static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {
            try {
                new Week4P1FoodOrder(order[0], order[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }
}