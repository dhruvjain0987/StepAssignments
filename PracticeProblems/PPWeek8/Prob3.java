package PracticeProblems.PPWeek8;
import java.time.*;
import java.util.*;

class Week8P3Room {
    String roomName;
    double rate;
    ArrayList<Week8P3Reservation> reservations = new ArrayList<>();

    Week8P3Room(String roomName, double rate) {
        this.roomName = roomName;
        this.rate = rate;
    }

    boolean isAvailable(LocalDate start, LocalDate end) {
        for (Week8P3Reservation r : reservations) {
            if (r.active && start.isBefore(r.endDate) &&
                end.isAfter(r.startDate))
                return false;
        }
        return true;
    }
}

class Week8P3Reservation {
    Week8P3Room room;
    String customer;
    LocalDate startDate, endDate;
    boolean active = true;
    LocalDate cancellationDeadline;

    Week8P3Reservation(Week8P3Room room, String customer,
                       LocalDate start, LocalDate end,
                       LocalDate cancellationDeadline) {
        this.room = room;
        this.customer = customer;
        this.startDate = start;
        this.endDate = end;
        this.cancellationDeadline = cancellationDeadline;
    }

    void cancel(LocalDate today) {
        if (!active) {
            System.out.println("Reservation already cancelled");
        } else if (today.isAfter(cancellationDeadline)) {
            System.out.println("Cancellation deadline has passed");
        } else {
            active = false;
            System.out.println("Reservation for " + room.roomName +
                " cancelled successfully.");
        }
    }
}

class Week8P3BookingManager {
    ArrayList<Week8P3Reservation> reservations = new ArrayList<>();

    void bookRoom(Week8P3Room room, String customer,
                  LocalDate start, LocalDate end,
                  LocalDate cancellationDeadline) {

        if (!end.isAfter(start)) {
            System.out.println("Invalid booking dates");
            return;
        }

        if (!room.isAvailable(start, end)) {
            System.out.println("Booking failed: " + room.roomName +
                " is not available for " + start + " to " + end);
            return;
        }

        Week8P3Reservation r = new Week8P3Reservation(
            room, customer, start, end, cancellationDeadline);

        room.reservations.add(r);
        reservations.add(r);

        long days = ChronoUnit.DAYS.between(start, end);
        double price = days * room.rate;

        System.out.printf("%s booked from %s to %s. Total price: $%.2f%n",
            room.roomName, start, end, price);
    }
}

public class Prob3 {
    public static void main(String[] args) {
        Week8P3Room deluxe =
            new Week8P3Room("Deluxe Room 101", 200);

        Week8P3Room standard =
            new Week8P3Room("Standard Room 205", 150);

        Week8P3BookingManager manager =
            new Week8P3BookingManager();

        LocalDate start1 = LocalDate.of(2024, 12, 1);
        LocalDate end1 = LocalDate.of(2024, 12, 5);

        LocalDate start2 = LocalDate.of(2024, 12, 3);
        LocalDate end2 = LocalDate.of(2024, 12, 7);

        manager.bookRoom(deluxe, "John", start1, end1,
            LocalDate.of(2024, 11, 25));

        manager.bookRoom(standard, "Sam", start2, end2,
            LocalDate.of(2024, 11, 25));

        manager.bookRoom(deluxe, "Alex", start2, end2,
            LocalDate.of(2024, 11, 25));

        deluxe.reservations.get(0).cancel(
            LocalDate.of(2024, 11, 20));
    }
}