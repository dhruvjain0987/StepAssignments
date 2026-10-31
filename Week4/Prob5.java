package Week4;

public class Prob5 {
    public static void main(String[] args) {
        Week4P5DeliveryAccount[] accounts = {
            new Week4P5PremiumAccount("STU001", 500),
            null,
            new Week4P5DeliveryAccount("STU002", 300)
        };

        double[] amounts = {500, 400, 300};
        int[] delays = {10, 5, 0};

        Week4P5DeliveryAccount.processBatch(accounts, amounts, delays);
    }
}

class Week4P5DeliveryAccount {
    protected String studentId;
    protected double orderValue;

    private static int accountCounter;

    static {
        accountCounter = 0;
    }

    public Week4P5DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
        accountCounter++;
    }

    public Week4P5DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0)
            throw new IllegalArgumentException("Invalid delay");

        return orderValue * delayMinutes * 0.01;
    }

    void processAccount(Week4P5DeliveryAccount account,
                        double amount, int delayMinutes) {
        double fee = amount * delayMinutes * 0.01;
        System.out.println("Surge fee for " + studentId + ": " + fee);
    }

    static void processBatch(Week4P5DeliveryAccount[] accounts,
                             double[] amounts,
                             int[] delayMinutesArray) {
        if (accounts.length != amounts.length ||
            accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException("Array lengths must match");
        }

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double totalFees = 0;

        for (int i = 0; i < accounts.length; i++) {
            Week4P5DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            double fee = account.calculateSurgeFee(delayMinutesArray[i]);

            if (account instanceof Week4P5PremiumAccount) {
                premium++;
                fee *= 0.5;
            } else {
                regular++;
            }

            totalFees += fee;
            processed++;
        }

        System.out.println(processed + " processed | " +
                           nullSkipped + " null skipped | " +
                           premium + " premium | " +
                           regular + " regular | grand total surge fees = " +
                           totalFees);
    }
}

class Week4P5PremiumAccount extends Week4P5DeliveryAccount {
    public Week4P5PremiumAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
}