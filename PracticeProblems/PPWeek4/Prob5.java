package PracticeProblems.PPWeek4;


class Week4P5BusTicketAccount {
    static int accountCount;

    static {
        accountCount = 0;
    }

    String bookingId;
    double ticketFare, amountPaid;

    Week4P5BusTicketAccount(String bookingId, double ticketFare) {
        if (ticketFare < 0)
            throw new IllegalArgumentException("Invalid fare");

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
        accountCount++;
    }

    Week4P5BusTicketAccount(String bookingId) {
        this(bookingId, 0);
    }

    final double calculatePenalty(int minutesLate) {
        if (minutesLate < 0)
            throw new IllegalArgumentException("Invalid minutes");

        return ticketFare * 0.01 * minutesLate;
    }

    void processAccount(Week4P5BusTicketAccount account,
                        double amount, int minutesLate) {
        if (account == null) return;

        double penalty = calculatePenalty(minutesLate);

        if (account instanceof Week4P5SleeperAccount)
            penalty *= 0.5;

        account.amountPaid += amount;

        System.out.println(account.bookingId +
            " | Penalty: Rs " + penalty);
    }

    static void processBatch(Week4P5BusTicketAccount[] accounts,
                             double[] amounts, int[] minutesLateArray) {
        if (accounts.length != amounts.length ||
            accounts.length != minutesLateArray.length) {
            System.out.println("Invalid batch: array lengths do not match");
            return;
        }

        int processed = 0, nullSkipped = 0;
        int sleeper = 0, regular = 0;
        double totalPenalties = 0;

        for (int i = 0; i < accounts.length; i++) {
            Week4P5BusTicketAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            try {
                double penalty = account.calculatePenalty(minutesLateArray[i]);

                if (account instanceof Week4P5SleeperAccount) {
                    penalty *= 0.5;
                    sleeper++;
                } else {
                    regular++;
                }

                account.amountPaid += amounts[i];
                totalPenalties += penalty;
                processed++;

                System.out.println(account.bookingId +
                    " | Penalty: Rs " + penalty);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid account data at index " + i);
            }
        }

        System.out.println(processed + " processed | " +
            nullSkipped + " null skipped | " +
            sleeper + " sleeper | " + regular + " regular");

        System.out.println("Grand total penalties = Rs " + totalPenalties);
    }
}

class Week4P5SleeperAccount extends Week4P5BusTicketAccount {
    Week4P5SleeperAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }
}

public class Prob5 {
    public static void main(String[] args) {
        Week4P5BusTicketAccount[] accounts = {
            new Week4P5SleeperAccount("BK001", 2000),
            null,
            new Week4P5BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {1200, 900, 700};
        int[] minutesLate = {10, 5, 0};

        Week4P5BusTicketAccount.processBatch(
            accounts, amounts, minutesLate);
    }
}