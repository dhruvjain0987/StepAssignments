package PracticeProblems.PPWeek8;


abstract class Week8P2Vehicle {
    String name;
    double ratePerDay;
    boolean available = true;

    Week8P2Vehicle(String name, double ratePerDay) {
        this.name = name;
        this.ratePerDay = ratePerDay;
    }

    abstract double calculateCharge(int days);
}

class Week8P2StandardCar extends Week8P2Vehicle {
    Week8P2StandardCar(String name) {
        super(name, 50);
    }

    double calculateCharge(int days) {
        return ratePerDay * days;
    }
}

class Week8P2LuxuryCar extends Week8P2Vehicle {
    Week8P2LuxuryCar(String name) {
        super(name, 100);
    }

    double calculateCharge(int days) {
        return ratePerDay * days;
    }
}

class Week8P2SUV extends Week8P2Vehicle {
    Week8P2SUV(String name) {
        super(name, 80);
    }

    double calculateCharge(int days) {
        return ratePerDay * days;
    }
}

class Week8P2Rental {
    Week8P2Vehicle vehicle;
    int days;
    double amount;

    Week8P2Rental(Week8P2Vehicle vehicle, int days) {
        this.vehicle = vehicle;
        this.days = days;
        amount = vehicle.calculateCharge(days);
    }
}

class Week8P2RentalService {
    void rentVehicle(Week8P2Vehicle vehicle, int days) {
        if (!vehicle.available) {
            System.out.println(vehicle.name + " is not available");
            return;
        }

        if (days <= 0) {
            System.out.println("Invalid rental duration");
            return;
        }

        Week8P2Rental rental = new Week8P2Rental(vehicle, days);
        vehicle.available = false;

        System.out.printf("%s rented for %d days. Total charge: $%.2f%n",
            vehicle.name, days, rental.amount);
    }

    void returnVehicle(Week8P2Vehicle vehicle) {
        if (vehicle.available) {
            System.out.println(vehicle.name + " was not rented");
            return;
        }

        vehicle.available = true;
        System.out.println(vehicle.name + " returned. Now available.");
    }
}

public class Prob2 {
    public static void main(String[] args) {
        Week8P2RentalService service = new Week8P2RentalService();

        Week8P2LuxuryCar luxury =
            new Week8P2LuxuryCar("Luxury Car A");

        Week8P2StandardCar standard =
            new Week8P2StandardCar("Standard Car B");

        service.rentVehicle(luxury, 3);
        service.rentVehicle(standard, 5);
        service.returnVehicle(luxury);

        service.rentVehicle(luxury, 2);
    }
}