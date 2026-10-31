package Week3;

public class Prob5 {
    public static void main(String[] args) {
        Week3P5Employee e1 =
            new Week3P5ManagerEmployee("E101", "Divya", 70000, 8000);

        Week3P5Employee e2 =
            new Week3P5Employee("E102", "Karan", 40000);

        Week3P5Employee e3 =
            new Week3P5InternEmployee("E103", "Meera", 12000, 10000);

        Week3P5ParkingSlot[] slots = {
            new Week3P5ParkingSlot("A1", 1, 0),
            new Week3P5ParkingSlot("A2", 1, 0)
        };

        Week3P5ParkingSlot slot1 =
            Week3P5ParkingSlot.findAvailableSlot(slots);
        if (slot1 != null)
            slot1.allot();

        Week3P5ParkingSlot slot2 =
            Week3P5ParkingSlot.findAvailableSlot(slots);
        if (slot2 != null)
            slot2.allot();

        Week3P5CompanyEmployeeRecord r1 =
            new Week3P5CompanyEmployeeRecord("Divya", "E101", e1, slot1);

        Week3P5CompanyEmployeeRecord r2 =
            new Week3P5CompanyEmployeeRecord("Karan", "E102", e2, slot2);

        Week3P5CompanyEmployeeRecord r3 =
            new Week3P5CompanyEmployeeRecord("Meera", "E103", e3, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());

        System.out.println("Total records: " +
                           Week3P5CompanyEmployeeRecord.totalRecords);
    }
}

class Week3P5Employee {
    private String empId;
    private String empName;
    private double salary;

    public Week3P5Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class Week3P5ManagerEmployee extends Week3P5Employee {
    private double teamBonus;

    public Week3P5ManagerEmployee(String empId, String empName,
                                  double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class Week3P5InternEmployee extends Week3P5Employee {
    private double stipendCap;

    public Week3P5InternEmployee(String empId, String empName,
                                 double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

class Week3P5ParkingSlot {
    private String slotNo;
    private int capacity;
    private int occupiedCount;

    public Week3P5ParkingSlot(String slotNo, int capacity,
                              int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot() {
        if (occupiedCount < capacity)
            occupiedCount++;
    }

    static Week3P5ParkingSlot findAvailableSlot(
        Week3P5ParkingSlot[] slots) {

        for (Week3P5ParkingSlot slot : slots) {
            if (slot != null && slot.occupiedCount < slot.capacity)
                return slot;
        }

        return null;
    }

    String getSlotNo() {
        return slotNo;
    }
}

class Week3P5CompanyEmployeeRecord {
    String name;
    String empId;
    Week3P5Employee employee;
    Week3P5ParkingSlot slot;

    static int totalRecords = 0;

    public Week3P5CompanyEmployeeRecord(
        String name, String empId,
        Week3P5Employee employee, Week3P5ParkingSlot slot) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;

        totalRecords++;
    }

    String fullProfile() {
        double pay;

        if (employee instanceof Week3P5ManagerEmployee) {
            Week3P5ManagerEmployee manager =
                (Week3P5ManagerEmployee) employee;
            pay = manager.effectiveSalary();

        } else if (employee instanceof Week3P5InternEmployee) {
            Week3P5InternEmployee intern =
                (Week3P5InternEmployee) employee;
            pay = intern.effectiveSalary();

        } else {
            pay = employee.getSalary();
        }

        String parking;

        if (slot == null)
            parking = "no parking assigned";
        else
            parking = slot.getSlotNo();

        return name + " | Pay: Rs " + pay + " | Slot: " + parking;
    }
}