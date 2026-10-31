package PracticeProblems.PPWeek3;


class Week3P2FeeAccount {
    private String regNo;
    private double totalFee, amountPaid;

    Week3P2FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment");
            return;
        }
        amountPaid += amount;
    }

    double getDue() {
        return Math.max(0, totalFee - amountPaid);
    }
}

class Week3P2HostelFeeAccount extends Week3P2FeeAccount {
    Week3P2HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}

class Week3P2ScholarshipFeeAccount extends Week3P2FeeAccount {
    private double scholarshipPercent;

    Week3P2ScholarshipFeeAccount(String regNo, double totalFee,
                                 double amountPaid, double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        return getDue() * (1 - scholarshipPercent / 100);
    }
}

public class Prob2 {
    public static void main(String[] args) {
        Week3P2FeeAccount plain =
            new Week3P2FeeAccount("101", 150000, 150000);

        Week3P2HostelFeeAccount hostel =
            new Week3P2HostelFeeAccount("102", 200000, 60000);

        Week3P2ScholarshipFeeAccount scholarship =
            new Week3P2ScholarshipFeeAccount("103", 180000, 0, 20);

        Week3P2FeeAccount[] accounts = {plain, hostel, scholarship};

        for (Week3P2FeeAccount a : accounts) {
            if (a instanceof Week3P2ScholarshipFeeAccount) {
                Week3P2ScholarshipFeeAccount s =
                    (Week3P2ScholarshipFeeAccount) a;
                System.out.println("Scholarship account effective due: Rs " +
                    s.effectiveDue());
            } else if (a instanceof Week3P2HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + a.getDue());
            } else {
                System.out.println("Plain account due: Rs " + a.getDue());
            }
        }
    }
}
