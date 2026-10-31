package Week4;

public class Prob2 {
    public static void main(String[] args) {
        Week4P2DeliverySlot slot1 =
            new Week4P2DeliverySlot("ORD101", "13:00-14:00");

        Week4P2DeliverySlot slot2 =
            new Week4P2DeliverySlot("ORD102");

        System.out.println(slot1.isPeakHour());
        System.out.println(slot2.isPeakHour());
    }
}

class Week4P2DeliverySlot {
    private String orderId;
    private String timeSlot;

    public Week4P2DeliverySlot(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    public Week4P2DeliverySlot(String orderId) {
        this(orderId, "ASAP");
    }

    boolean isPeakHour() {
        return timeSlot.equals("12:00-13:00") ||
               timeSlot.equals("13:00-14:00") ||
               timeSlot.equals("19:00-20:00") ||
               timeSlot.equals("20:00-21:00");
    }
}