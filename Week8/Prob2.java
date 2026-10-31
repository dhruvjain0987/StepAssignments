package Week8;
import java.util.*;

interface ShippingType {
    double calculate(double weight);
}

class Standard implements ShippingType {
    public double calculate(double weight) {
        return 40 + 10 * weight;
    }
}

class Express implements ShippingType {
    public double calculate(double weight) {
        return 80 + 15 * weight;
    }
}

class Fragile implements ShippingType {
    public double calculate(double weight) {
        return 40 + 10 * weight + 50;
    }
}

interface NotificationChannel {
    void notify(String message);
}

class SmsChannel implements NotificationChannel {
    public void notify(String message) {
        System.out.println("[SMS] " + message);
    }
}

class EmailChannel implements NotificationChannel {
    public void notify(String message) {
        System.out.println("[Email] " + message);
    }
}

class Parcel {
    String id;
    double weight;
    double charge;
    String status = "BOOKED";
    ArrayList<NotificationChannel> channels = new ArrayList<>();

    Parcel(String id, double weight, ShippingType type) {
        this.id = id;
        this.weight = weight;
        charge = type.calculate(weight);
    }

    void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    void updateStatus(String next) {
        String[] states = {
            "BOOKED", "PICKED_UP", "IN_TRANSIT",
            "OUT_FOR_DELIVERY", "DELIVERED"
        };

        int current = Arrays.asList(states).indexOf(status);
        if (current < 0 || current == states.length - 1 ||
                !states[current + 1].equals(next)) {
            System.out.println("Invalid transition: " +
                    status + " → " + next + " is not allowed.");
            return;
        }

        status = next;

        for (NotificationChannel c : channels) {
            c.notify(id + " is now " + status + ".");
        }
    }

    void cancel() {
        if (status.equals("BOOKED")) {
            status = "CANCELLED";
            System.out.println("Parcel " + id + " cancelled.");
        } else {
            System.out.println("Cancellation failed: " +
                    id + " can be cancelled only while BOOKED.");
        }
    }
}

public class Prob2 {
    public static void main(String[] args) {
        Parcel p = new Parcel("P101", 2, new Express());

        p.subscribe(new SmsChannel());
        p.subscribe(new EmailChannel());

        System.out.println("Parcel P101 booked (Express, 2 kg).");
        System.out.printf("Charge: ₹%.2f%n", p.charge);

        p.updateStatus("PICKED_UP");
        p.cancel();
        p.updateStatus("IN_TRANSIT");
        p.updateStatus("DELIVERED");
    }
}