package PracticeProblems.PPWeek8;
import java.time.*;

abstract class Week8P4Employee {
    String name;

    Week8P4Employee(String name) {
        this.name = name;
    }

    abstract int getMaxLeaveDays();
}

class Week8P4FullTime extends Week8P4Employee {
    Week8P4FullTime(String name) {
        super(name);
    }

    int getMaxLeaveDays() {
        return 30;
    }
}

class Week8P4PartTime extends Week8P4Employee {
    Week8P4PartTime(String name) {
        super(name);
    }

    int getMaxLeaveDays() {
        return 15;
    }
}

class Week8P4Contract extends Week8P4Employee {
    Week8P4Contract(String name) {
        super(name);
    }

    int getMaxLeaveDays() {
        return 10;
    }
}

class Week8P4LeaveRequest {
    Week8P4Employee employee;
    LocalDate startDate, endDate;
    String status = "Pending";

    Week8P4LeaveRequest(Week8P4Employee employee,
                        LocalDate startDate, LocalDate endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    void review(String newStatus) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change status: " +
                status + " request cannot revert to Pending");
            return;
        }

        if (!newStatus.equals("Approved") &&
            !newStatus.equals("Rejected")) {
            System.out.println("Invalid status");
            return;
        }

        status = newStatus;

        System.out.println("Leave request for " + employee.name +
            " " + status.toLowerCase() + ". Status: " + status);
    }
}

class Week8P4LeaveManager {
    Week8P4LeaveRequest submitRequest(Week8P4Employee employee,
                                      LocalDate start, LocalDate end) {
        if (end.isBefore(start)) {
            System.out.println("Invalid leave dates");
            return null;
        }

        long days = ChronoUnit.DAYS.between(start, end) + 1;

        if (days > employee.getMaxLeaveDays()) {
            System.out.println("Leave request exceeds allowed days");
            return null;
        }

        Week8P4LeaveRequest request =
            new Week8P4LeaveRequest(employee, start, end);

        System.out.println("Leave request submitted by " +
            employee.name + " for " + start + " to " + end +
            ". Status: Pending");

        return request;
    }
}

public class Prob4 {
    public static void main(String[] args) {
        Week8P4LeaveManager manager = new Week8P4LeaveManager();

        Week8P4FullTime john =
            new Week8P4FullTime("John Doe");

        Week8P4PartTime jane =
            new Week8P4PartTime("Jane Smith");

        Week8P4LeaveRequest r1 = manager.submitRequest(
            john,
            LocalDate.of(2024, 10, 10),
            LocalDate.of(2024, 10, 12));

        r1.review("Approved");

        Week8P4LeaveRequest r2 = manager.submitRequest(
            jane,
            LocalDate.of(2024, 11, 1),
            LocalDate.of(2024, 11, 5));

        r1.review("Pending");
    }
}