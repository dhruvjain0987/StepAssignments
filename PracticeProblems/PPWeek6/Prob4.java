package PracticeProblems.PPWeek6;


class Week6P4EventTicket {
    protected double basePrice, amountPaid;

    Week6P4EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    double getBalanceDue() {
        return Math.max(0, basePrice - amountPaid);
    }

    void printTicket() {
        System.out.print("Standard | Balance: " + getBalanceDue());
    }
}

class Week6P4WorkshopTicket extends Week6P4EventTicket {
    String track;

    Week6P4WorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    @Override
    void printTicket() {
        System.out.print("Workshop | Track: " + track +
            " | Balance: " + getBalanceDue());
    }
}

public class Prob4 {
    static String batchPrint(Week6P4EventTicket[] tickets) {
        StringBuilder sb = new StringBuilder();

        for (Week6P4EventTicket t : tickets) {
            StringBuilder report = new StringBuilder();

            // Polymorphic method call
            java.io.ByteArrayOutputStream output =
                new java.io.ByteArrayOutputStream();
            java.io.PrintStream old = System.out;
            System.setOut(new java.io.PrintStream(output));

            t.printTicket();

            System.setOut(old);
            report.append(output.toString());

            if (t instanceof Week6P4WorkshopTicket) {
                Week6P4WorkshopTicket w =
                    (Week6P4WorkshopTicket) t;
                report.append(" [Track via downcast: ")
                    .append(w.track).append("]");
            }

            sb.append(report).append(" | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Week6P4EventTicket[] tickets = {
            new Week6P4EventTicket(500),
            new Week6P4WorkshopTicket(1200, "AI/ML")
        };

        System.out.println(batchPrint(tickets));
    }
}