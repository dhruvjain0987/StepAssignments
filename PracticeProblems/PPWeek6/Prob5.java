package PracticeProblems.PPWeek6;

class Week6P5EventTicket {
    private static int ticketCounter = 1000;

    final String ticketId;
    protected double basePrice, amountPaid;

    Week6P5EventTicket(double basePrice) {
        if (basePrice <= 0)
            throw new IllegalArgumentException("Invalid price");

        ticketCounter++;
        ticketId = "TCK-" + ticketCounter;
        this.basePrice = basePrice;
    }

    void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
    }

    void pay(double amount, String mode) {
        System.out.println("Payment mode: " + mode);
        pay(amount);
    }

    double getBalanceDue() {
        return Math.max(0, basePrice - amountPaid);
    }

    static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5)
            return false;

        if (code.charAt(0) != 'F')
            return false;

        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i)))
                return false;
        }

        return Character.isUpperCase(code.charAt(4));
    }

    static int getTicketsIssued() {
        return ticketCounter - 1000;
    }

    static String processNightlySettlement(Week6P5EventTicket[] tickets) {
        int processed = 0, skipped = 0, group = 0, individual = 0;

        for (Week6P5EventTicket t : tickets) {
            if (t == null) {
                skipped++;
                continue;
            }

            processed++;

            if (t instanceof Week6P5GroupTicket)
                group++;
            else
                individual++;
        }

        return processed + " processed | " + skipped +
            " null skipped | " + group + " group | " +
            individual + " individual";
    }
}

class Week6P5GroupTicket extends Week6P5EventTicket {
    int groupSize;

    Week6P5GroupTicket(double basePrice, int groupSize) {
        super(basePrice);

        if (groupSize <= 0)
            throw new IllegalArgumentException("Invalid group size");

        this.groupSize = groupSize;
    }
}

public class Prob5 {
    public static void main(String[] args) {
        Week6P5EventTicket t1 = new Week6P5EventTicket(500);

        System.out.println(t1.ticketId);
        System.out.println(Week6P5EventTicket.getTicketsIssued());

        System.out.println(
            Week6P5EventTicket.isValidPromoCode("F123A"));
        System.out.println(
            Week6P5EventTicket.isValidPromoCode("F12A"));
        System.out.println(
            Week6P5EventTicket.isValidPromoCode("X123A"));

        t1.pay(200);
        t1.pay(200, "UPI");
        System.out.println(t1.getBalanceDue());

        Week6P5EventTicket[] tickets = {
            new Week6P5GroupTicket(2000, 5),
            null,
            new Week6P5EventTicket(500)
        };

        System.out.println(
            Week6P5EventTicket.processNightlySettlement(tickets));
    }
}