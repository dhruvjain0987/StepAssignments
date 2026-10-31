package PracticeProblems.PPWeek7;

abstract class Week7P1PaymentMethod {
    private static int counter = 1000;
    private final String transactionId;

    Week7P1PaymentMethod() {
        transactionId = "TXN-" + (++counter);
    }

    public abstract String processPayment(double amount);

    String processPayment(double amount, String note) {
        return processPayment(amount) + " (" + note + ")";
    }

    public String getTransactionId() {
        return transactionId;
    }
}

class Week7P1CreditCardPayment extends Week7P1PaymentMethod {
    String lastFour;

    Week7P1CreditCardPayment(String lastFour) {
        this.lastFour = lastFour;
    }

    @Override
    public String processPayment(double amount) {
        return "Charged $" + amount + " to card ending " +
            lastFour + " - Txn " + getTransactionId();
    }
}

class Week7P1CashPayment extends Week7P1PaymentMethod {
    @Override
    public String processPayment(double amount) {
        return "Received $" + amount + " in cash - Txn " +
            getTransactionId();
    }
}

public class Prob1 {
    static void printConfirmation(Week7P1PaymentMethod payment,
                                  double amount) {
        System.out.println(payment.processPayment(amount));
    }

    public static void main(String[] args) {
        Week7P1CreditCardPayment cc =
            new Week7P1CreditCardPayment("4471");

        Week7P1CashPayment cash = new Week7P1CashPayment();

        System.out.println(cc.processPayment(250));
        System.out.println(cash.processPayment(40));
        System.out.println(cc.processPayment(250, "Birthday gift"));

        Week7P1PaymentMethod ref = cc; // Upcasting
        printConfirmation(ref, 250);
    }
}