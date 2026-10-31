package PracticeProblems.PPWeek4;
import java.util.*;

class Week4P2FareSplitter {
    String tripId;
    double totalFare;
    int passengerCount;

    Week4P2FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0 || passengerCount <= 0)
            throw new IllegalArgumentException("Invalid fare or passenger count");

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    Week4P2FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1);
    }

    Week4P2FareSplitter(String tripId) {
        this(tripId, 0, 2);
    }

    double[] fareBreakdown() {
        double[] shares = new double[passengerCount];

        int totalPaise = (int) Math.round(totalFare * 100);
        int each = totalPaise / passengerCount;
        int remainder = totalPaise % passengerCount;

        for (int i = 0; i < passengerCount; i++) {
            int share = each;
            if (i >= passengerCount - remainder)
                share++;
            shares[i] = share / 100.0;
        }

        return shares;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }
}

public class Prob2 {
    public static void main(String[] args) {
        Week4P2FareSplitter f1 =
            new Week4P2FareSplitter("TRIP001", 100000, 3);

        System.out.println(Arrays.toString(f1.fareBreakdown()));

        Week4P2FareSplitter f2 =
            new Week4P2FareSplitter("TRIP003");

        System.out.println(Arrays.toString(f2.fareBreakdown()));
    }
}