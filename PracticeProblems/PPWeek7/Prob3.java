package PracticeProblems.PPWeek7;
abstract class Week7P3StaffMember {
    private double baseSalary;
    protected double bonusRate;

    Week7P3StaffMember(double baseSalary) {
        this(baseSalary, 0.10);
    }

    Week7P3StaffMember(double baseSalary, double bonusRate) {
        if (baseSalary < 0 || bonusRate < 0)
            throw new IllegalArgumentException("Invalid values");

        this.baseSalary = baseSalary;
        this.bonusRate = bonusRate;
    }

    abstract double calculateBonus();

    double getSalary() {
        return baseSalary;
    }

    void setSalary(double baseSalary) {
        if (baseSalary >= 0)
            this.baseSalary = baseSalary;
        else
            System.out.println("Salary rejected");
    }
}

interface Week7P3Auditable {
    String auditRecord();
}

class Week7P3TeamLead extends Week7P3StaffMember
    implements Week7P3Auditable {

    int teamSize;

    Week7P3TeamLead(double salary, int teamSize) {
        super(salary);
        this.teamSize = teamSize;
    }

    Week7P3TeamLead(double salary, double bonusRate, int teamSize) {
        super(salary, bonusRate);
        this.teamSize = teamSize;
    }

    @Override
    double calculateBonus() {
        return getSalary() * bonusRate;
    }

    @Override
    public String auditRecord() {
        return "TeamLead audit: " + teamSize +
            " team members, salary $" + getSalary();
    }
}

public class Prob3 {
    static String getAuditIfApplicable(Week7P3StaffMember s) {
        if (s instanceof Week7P3Auditable) {
            Week7P3Auditable a = (Week7P3Auditable) s;
            return a.auditRecord();
        }
        return "No audit required";
    }

    public static void main(String[] args) {
        Week7P3TeamLead t = new Week7P3TeamLead(60000, 5);
        Week7P3TeamLead t2 = new Week7P3TeamLead(60000, 0.20, 5);

        System.out.println(t.calculateBonus());
        System.out.println(t2.calculateBonus());

        t.setSalary(-5000);

        Week7P3StaffMember ref = t; // Upcasting
        System.out.println(getAuditIfApplicable(ref));
    }
}