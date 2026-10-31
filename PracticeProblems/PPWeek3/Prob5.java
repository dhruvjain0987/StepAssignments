package PracticeProblems.PPWeek3;

class Week3P5FeeAccount {
    double totalFee, amountPaid;

    Week3P5FeeAccount(double totalFee, double amountPaid) {
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Payment rejected");
            return;
        }
        amountPaid += amount;
    }

    double getDue() {
        return Math.max(0, totalFee - amountPaid);
    }
}

class Week3P5HostelFeeAccount extends Week3P5FeeAccount {
    Week3P5HostelFeeAccount(double totalFee, double amountPaid) {
        super(totalFee, amountPaid);
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}

class Week3P5HostelRoom {
    String roomNo;
    int beds, occupied;

    Week3P5HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    boolean allot() {
        if (occupied < beds) {
            occupied++;
            return true;
        }
        return false;
    }
}

class Week3P5SrmStudent {
    String name, regNo;
    Week3P5HostelFeeAccount feeAccount;
    Week3P5HostelRoom room;
    static int totalStudents = 0;

    Week3P5SrmStudent(String name, String regNo,
                      Week3P5HostelFeeAccount feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        totalStudents++;
    }

    String fullStatus() {
        String roomNo = (room == null) ? "unallotted" : room.roomNo;
        return name + " | Due: Rs " + feeAccount.getDue() +
            " | Room: " + roomNo;
    }
}

public class Prob5 {
    public static void main(String[] args) {
        Week3P5HostelRoom r1 =
            new Week3P5HostelRoom("C-214", 1, 0);
        Week3P5HostelRoom r2 =
            new Week3P5HostelRoom("C-507", 1, 0);

        Week3P5SrmStudent s1 = new Week3P5SrmStudent("Ravi", "101",
            new Week3P5HostelFeeAccount(200000, 60000));

        Week3P5SrmStudent s2 = new Week3P5SrmStudent("Anitha", "102",
            new Week3P5HostelFeeAccount(200000, 20000));

        Week3P5SrmStudent s3 = new Week3P5SrmStudent("Karthik", "103",
            new Week3P5HostelFeeAccount(200000, 0));

        if (r1.allot()) s1.room = r1;
        if (r2.allot()) s2.room = r2;

        s1.feeAccount.pay(0);
        s2.feeAccount.pay(20000);
        s3.feeAccount.pay(-500);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " +
            Week3P5SrmStudent.totalStudents);
    }
}