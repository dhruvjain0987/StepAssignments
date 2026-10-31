package Week4;

public class Prob4 {
    public static void main(String[] args) {
        Week4P4SurgeFeeCalculator calculator =
            new Week4P4SurgeFeeCalculator(1.0);

        System.out.println(calculator.calculateSurgeFee(500, 0));
        System.out.println(calculator.calculateSurgeFee(500, 1));
        System.out.println(calculator.calculateSurgeFee(500, 16));
    }
}

final class Week4P4SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public Week4P4SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (delayMinutes == 0)
            return 0.0;

        int firstBracket = Math.min(delayMinutes, 5);
        int secondBracket = Math.min(Math.max(delayMinutes - 5, 0), 10);
        int thirdBracket = Math.max(delayMinutes - 15, 0);

        double fee =
            orderValue * firstBracket * 0.005 +
            orderValue * secondBracket * 0.01 +
            orderValue * thirdBracket * 0.02;

        double minimumFee = orderValue * minimumSurgePercent / 100.0;

        return Math.max(fee, minimumFee);
    }
}