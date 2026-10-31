package PracticeProblems.PPWeek6;
import java.util.*;

class Week6P3EventTicket {
    protected double basePrice, amountPaid;
    private double[] lateFeeHistory = new double[10];
    private int feeCount = 0;

    Week6P3EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
    }

    protected void applyLateFee(double amount) {
        if (feeCount < lateFeeHistory.length && amount > 0) {
            lateFeeHistory[feeCount++] = amount;
        }
    }

    double getBalanceDue() {
        double totalFee = 0;

        for (int i = 0; i < feeCount; i++)
            totalFee += lateFeeHistory[i];

        return Math.max(0, basePrice - amountPaid + totalFee);
    }

    double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }
}

class Week6P3WorkshopTicket extends Week6P3EventTicket {
    Week6P3WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class Prob3 {
    public static void main(String[] args) {
        Week6P3WorkshopTicket w =
            new Week6P3WorkshopTicket(1200);

        w.pay(1200);
        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();
        System.out.println(Arrays.toString(history));

        history[0] = 999;
        System.out.println(Arrays.toString(w.getLateFeeHistory()));
    }
}