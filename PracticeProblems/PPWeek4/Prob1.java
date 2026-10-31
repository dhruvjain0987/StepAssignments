package PracticeProblems.PPWeek4;

import java.util.*;

class Week4P1BusTicket {
    String passengerName, destination;
    boolean checkedIn = false;

    Week4P1BusTicket(String passengerName, String destination) {
        if (passengerName == null || destination == null ||
            passengerName.trim().isEmpty() || destination.trim().isEmpty() ||
            !passengerName.matches("[a-zA-Z ]+") ||
            !destination.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Invalid booking");
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
    }

    void markCheckedIn() {
        if (!checkedIn) {
            checkedIn = true;
            System.out.println("Checked in successfully");
        } else {
            System.out.println("Already checked in");
        }
    }

    static void processBatch(String[][] rawBookings) {
        Set<String> accepted = new HashSet<>();
        int valid = 0, rejected = 0, duplicates = 0;

        for (String[] booking : rawBookings) {
            try {
                Week4P1BusTicket ticket =
                    new Week4P1BusTicket(booking[0], booking[1]);

                String key = ticket.passengerName.toLowerCase() + "|" +
                    ticket.destination.toLowerCase();

                if (accepted.contains(key)) {
                    duplicates++;
                } else {
                    accepted.add(key);
                    valid++;
                }
            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " +
            rejected + " | Duplicates skipped: " + duplicates);
    }
}

public class Prob1 {
    public static void main(String[] args) {
        String[][] bookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        Week4P1BusTicket.processBatch(bookings);
    }
}