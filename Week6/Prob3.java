package Week6;
import java.util.Arrays;

public class Prob3 {
    public static void main(String[] args) {
        Week6P3RunnerEntry r =
            new Week6P3RunnerEntry("BIB2001", 80, "Open 10K");

        r.pay(30);
        r.applyLateFee(20);

        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        System.out.println(Arrays.toString(history));

        history[0] = 999;

        System.out.println(Arrays.toString(r.getLateFeeHistory()));
    }
}

class Week6P3RaceEntry {
    protected String bibNumber;
    protected double balanceDue;

    private double[] lateFeeHistory = new double[10];
    private int feeCount = 0;

    public Week6P3RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }

        this.bibNumber = bibNumber;
        this.balanceDue = entryFee;
    }

    void pay(double amount) {
        balanceDue -= amount;
    }

    double getBalanceDue() {
        return balanceDue;
    }

    protected void applyLateFee(double amount) {
        balanceDue += amount;

        if (feeCount < 10) {
            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }
    }

    double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }
}

class Week6P3RunnerEntry extends Week6P3RaceEntry {
    private String category;

    public Week6P3RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}