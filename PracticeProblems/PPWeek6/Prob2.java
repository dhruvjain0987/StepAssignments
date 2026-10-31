package PracticeProblems.PPWeek6;


class Week6P2EventTicket {
    protected String attendeeId;
    protected double basePrice, amountPaid;

    Week6P2EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid ID");

        if (basePrice <= 0)
            throw new IllegalArgumentException("Invalid price");

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
    }

    double getBalanceDue() {
        return Math.max(0, basePrice - amountPaid);
    }

    void printTicket() {
        System.out.println("Standard Event Ticket | Balance Due: " +
            getBalanceDue());
    }
}

class Week6P2WorkshopTicket extends Week6P2EventTicket {
    protected String track;

    Week6P2WorkshopTicket(String id, double price, String track) {
        super(id, price);
        this.track = track;
    }

    @Override
    void printTicket() {
        System.out.println("Workshop Ticket | Track: " + track +
            " | Balance Due: " + getBalanceDue());
    }
}

class Week6P2PremiumWorkshopTicket extends Week6P2WorkshopTicket {
    double kitFee;

    Week6P2PremiumWorkshopTicket(String id, double price,
                                 String track, double kitFee) {
        super(id, price, track);
        this.kitFee = kitFee;
    }

    @Override
    void printTicket() {
        System.out.println("Premium Workshop Ticket | Track: " + track +
            " | Kit Fee: " + kitFee +
            " | Balance Due: " + getBalanceDue());
    }
}

class Week6P2HackathonTicket extends Week6P2EventTicket {
    String teamName;

    Week6P2HackathonTicket(String id, double price, String teamName) {
        super(id, price);
        this.teamName = teamName;
    }

    @Override
    void printTicket() {
        System.out.println("Hackathon Ticket | Team: " + teamName +
            " | Balance Due: " + getBalanceDue());
    }
}

public class Prob2 {
    static String classifyGeneration(Week6P2EventTicket ticket) {
        if (ticket instanceof Week6P2PremiumWorkshopTicket)
            return "Multilevel descendant (3 generations deep)";

        if (ticket instanceof Week6P2HackathonTicket)
            return "Hierarchical sibling (independent branch)";

        if (ticket instanceof Week6P2WorkshopTicket)
            return "Single inheritance";

        return "Base ticket";
    }

    static double getTotalBalanceDue(Week6P2EventTicket[] tickets) {
        double total = 0;

        for (Week6P2EventTicket t : tickets)
            total += t.getBalanceDue();

        return total;
    }

    public static void main(String[] args) {
        Week6P2EventTicket t1 =
            new Week6P2EventTicket("STU1", 500);

        Week6P2WorkshopTicket t2 =
            new Week6P2WorkshopTicket("STU2", 1200, "AI/ML");

        Week6P2PremiumWorkshopTicket t3 =
            new Week6P2PremiumWorkshopTicket(
                "STU3", 2000, "Cloud Native", 300);

        Week6P2HackathonTicket t4 =
            new Week6P2HackathonTicket(
                "STU4", 800, "Byte Force");

        Week6P2EventTicket[] tickets = {t1, t2, t3, t4};

        for (Week6P2EventTicket t : tickets)
            t.printTicket();

        System.out.println(classifyGeneration(t3));
        System.out.println(classifyGeneration(t4));

        System.out.println("Total due: " + getTotalBalanceDue(tickets));
    }
}