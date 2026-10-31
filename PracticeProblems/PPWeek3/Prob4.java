package PracticeProblems.PPWeek3;


class Week3P4BrokenStudent {
    static String name;
    static String regNo;
    static int attendance;

    Week3P4BrokenStudent(String name, String regNo, int attendance) {
        Week3P4BrokenStudent.name = name;
        Week3P4BrokenStudent.regNo = regNo;
        Week3P4BrokenStudent.attendance = attendance;
    }
}

class Week3P4SrmStudent {
    String name, regNo;
    int attendance;
    static String university = "SRM";
    static int admissionCount = 0;

    Week3P4SrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA2311003010" + (10 + admissionCount);
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }
}

public class Prob4 {
    public static void main(String[] args) {
        System.out.println("Broken version:");

        Week3P4BrokenStudent s1 =
            new Week3P4BrokenStudent("Ravi", "101", 82);
        Week3P4BrokenStudent s2 =
            new Week3P4BrokenStudent("Meera", "102", 74);

        System.out.println(Week3P4BrokenStudent.name);
        System.out.println(Week3P4BrokenStudent.name);
        System.out.println("(Ravi's data was overwritten)");

        System.out.println("\nFixed version:");

        Week3P4SrmStudent a = new Week3P4SrmStudent("Ravi", 82);
        Week3P4SrmStudent b = new Week3P4SrmStudent("Meera", 74);

        a.printIdCard();
        b.printIdCard();

        Week3P4SrmStudent.printTotalAdmissions();
    }
}