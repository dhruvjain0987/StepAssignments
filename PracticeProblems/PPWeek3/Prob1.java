package PracticeProblems.PPWeek3;


class Week3P1SrmStudent {
    String name, regNo;
    int attendance;

    Week3P1SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    // Static because average uses the whole student array.
    // isEligible is an instance method because attendance belongs to one student.
    static double classAverage(Week3P1SrmStudent[] students) {
        int sum = 0;
        for (Week3P1SrmStudent s : students)
            sum += s.attendance;
        return (double) sum / students.length;
    }
}

public class Prob1 {
    public static void main(String[] args) {
        Week3P1SrmStudent[] students = {
            new Week3P1SrmStudent("Ravi", "101", 82),
            new Week3P1SrmStudent("Anitha", "102", 68),
            new Week3P1SrmStudent("Karthik", "103", 91),
            new Week3P1SrmStudent("Meera", "104", 74),
            new Week3P1SrmStudent("Suresh", "105", 60)
        };

        for (Week3P1SrmStudent s : students) {
            System.out.println(s.name + " - " + s.attendance +
                "% - " + (s.isEligible() ? "Eligible" : "Detained"));
        }

        System.out.printf("Class average: %.1f%%%n",
            Week3P1SrmStudent.classAverage(students));
    }
}