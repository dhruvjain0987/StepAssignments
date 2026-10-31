package PracticeProblems.PPWeek4;


final class Week4P4BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    Week4P4BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0)
            throw new IllegalArgumentException("Invalid input");

        if (minutesLate == 0)
            return 0;

        double penalty = 0;

        int first = Math.min(minutesLate, 5);
        int second = Math.max(0, Math.min(minutesLate - 5, 10));
        int third = Math.max(0, minutesLate - 15);

        penalty += ticketFare * first * 0.005;
        penalty += ticketFare * second * 0.01;
        penalty += ticketFare * third * 0.02;

        double minimumPenalty = ticketFare * minimumPenaltyPercent / 100;

        return Math.max(penalty, minimumPenalty);
    }
}

public class Prob4 {
    public static void main(String[] args) {
        Week4P4BoardingPenaltyCalculator calculator =
            new Week4P4BoardingPenaltyCalculator(1);

        System.out.println(calculator.calculatePenalty(1000, 0));
        System.out.println(calculator.calculatePenalty(1000, 1));
        System.out.println(calculator.calculatePenalty(1000, 16));
    }
}
