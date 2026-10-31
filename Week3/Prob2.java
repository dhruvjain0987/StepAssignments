package Week3;

public class Prob2 {
    public static void main(String[] args) {
        Week3P2Employee e1 =
            new Week3P2Employee("E101", "Ravi", 40000);

        Week3P2Employee e2 =
            new Week3P2ManagerEmployee("E102", "Aman", 70000, 8000);

        Week3P2Employee e3 =
            new Week3P2InternEmployee("E103", "Priya", 12000, 10000);

        Week3P2Employee[] employees = {e1, e2, e3};

        for (Week3P2Employee e : employees) {
            if (e instanceof Week3P2ManagerEmployee) {
                Week3P2ManagerEmployee m =
                    (Week3P2ManagerEmployee) e;

                System.out.println("Manager effective pay: Rs " +
                                   m.effectiveSalary());
            } else if (e instanceof Week3P2InternEmployee) {
                Week3P2InternEmployee i =
                    (Week3P2InternEmployee) e;

                System.out.println("Intern effective pay: Rs " +
                                   i.effectiveSalary());
            } else {
                System.out.println("Plain employee pay: Rs " +
                                   e.getSalary());
            }
        }
    }
}

class Week3P2Employee {
    private String empId;
    private String empName;
    private double salary;

    public Week3P2Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class Week3P2ManagerEmployee extends Week3P2Employee {
    private double teamBonus;

    public Week3P2ManagerEmployee(String empId, String empName,
                                  double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class Week3P2InternEmployee extends Week3P2Employee {
    private double stipendCap;

    public Week3P2InternEmployee(String empId, String empName,
                                 double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}