package Week8;
import java.util.*;

interface CardPricingPlan {
    double getPrice(double price);
}

class CardDayScholarPlan implements CardPricingPlan {
    public double getPrice(double price) {
        return price;
    }
}

class CardHostellerPlan implements CardPricingPlan {
    public double getPrice(double price) {
        return price * 0.90;
    }
}

class CardStaffPlan implements CardPricingPlan {
    public double getPrice(double price) {
        return price * 0.80;
    }
}

class CardTransaction {
    String item;
    double amount;
    boolean refunded = false;

    CardTransaction(String item, double amount) {
        this.item = item;
        this.amount = amount;
    }
}

class CanteenSmartCard {
    String id;
    double balance = 0;
    boolean blocked = false;
    CardPricingPlan plan;
    ArrayList<CardTransaction> transactions = new ArrayList<>();

    CanteenSmartCard(String id, CardPricingPlan plan) {
        this.id = id;
        this.plan = plan;
    }

    void topUp(double amount) {
        if (blocked) {
            System.out.println("Top-up rejected: Card is blocked.");
            return;
        }

        if (amount < 100 || balance + amount > 5000) {
            System.out.println("Top-up rejected.");
            return;
        }

        balance += amount;
        transactions.add(new CardTransaction("Top-up", amount));

        System.out.printf("%s topped up with ₹%.2f.%n", id, amount);
        System.out.printf("Balance: ₹%.2f%n", balance);
    }

    CardTransaction purchase(String item, double price) {
        if (blocked) {
            System.out.println("Purchase failed: Card is blocked.");
            return null;
        }

        double charged = plan.getPrice(price);

        if (balance < charged) {
            System.out.printf(
                "Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).%n",
                charged, balance);
            return null;
        }

        balance -= charged;

        CardTransaction t = new CardTransaction(item, -charged);
        transactions.add(t);

        System.out.printf("%s purchased for ₹%.2f.%n", item, charged);
        System.out.printf("Balance: ₹%.2f%n", balance);

        return t;
    }

    void refund(CardTransaction t) {
        if (t == null) return;

        if (t.refunded) {
            System.out.println("Refund rejected: " +
                    t.item + " has already been refunded.");
            return;
        }

        t.refunded = true;
        balance += -t.amount;

        transactions.add(new CardTransaction("Refund", -t.amount));

        System.out.printf("Refund of ₹%.2f for %s processed.%n",
                -t.amount, t.item);
        System.out.printf("Balance: ₹%.2f%n", balance);
    }

    void block() {
        blocked = true;
    }

    void unblock() {
        blocked = false;
    }

    void miniStatement() {
        System.out.print("Mini-statement for " + id + ": ");

        for (int i = 0; i < transactions.size(); i++) {
            System.out.printf("%+.2f", transactions.get(i).amount);

            if (i < transactions.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf("%n= ₹%.2f.%n", balance);
    }
}

public class Prob5 {
    public static void main(String[] args) {
        CanteenSmartCard card = new CanteenSmartCard(
                "C-2045", new CardHostellerPlan());

        card.topUp(500);

        CardTransaction t1 = card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);
        card.purchase("Other Items", 400);

        card.refund(t1);
        card.refund(t1);

        card.miniStatement();
    }
}