package PracticeProblems.PPWeek6;

class Week6P1EventTicket {
    protected String attendeeId;
    protected double basePrice, amountPaid;

    Week6P1EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid attendee ID");

        if (basePrice <= 0)
            throw new IllegalArgumentException("Invalid price");

        this.attendeeId = attendeeId.trim();
        this.basePrice = basePrice;
    }

    void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
    }

    double getBalanceDue() {
        return Math.max(0, basePrice - amountPaid);
    }

    static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0, rejected = 0;

        for (String id : attendeeIds) {
            try {
                new Week6P1EventTicket(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}

class Week6P1WorkshopTicket extends Week6P1EventTicket {
    String track;

    Week6P1WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }
}

public class Prob1 {
    public static void main(String[] args) {
        Week6P1WorkshopTicket w =
            new Week6P1WorkshopTicket("STU2", 1200, "AI/ML");

        w.pay(500);
        System.out.println(w.getBalanceDue());

        String[] ids = {"STU1", "ST1", "STU2", " ", "STU3"};

        System.out.println(
            Week6P1EventTicket.registerBatch(ids, 500));
    }
}