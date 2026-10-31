package Week3;

public class Prob1 {
    public static void main(String[] args) {
        Week3P1BookIssue[] issues = {
            new Week3P1BookIssue("Clean Code", "Ravi", 18),
            new Week3P1BookIssue("Effective Java", "Aman", 5),
            new Week3P1BookIssue("Refactoring", "Priya", 0),
            new Week3P1BookIssue("DSA Handbook", "Karan", 21),
            new Week3P1BookIssue("Design Patterns", "Meera", 9)
        };

        for (Week3P1BookIssue book : issues) {
            System.out.print(book.getTitle() + " - " +
                             book.getDaysOverdue() + " days - ");

            if (book.isSeverelyOverdue())
                System.out.println("Severely overdue");
            else
                System.out.println("OK");
        }

        System.out.println("Total fine collected: Rs " +
            Week3P1BookIssue.totalFineCollected(issues));
    }
}

class Week3P1BookIssue {
    private String title;
    private String borrowerName;
    private int daysOverdue;

    public Week3P1BookIssue(String title, String borrowerName,
                            int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    double fineAmount() {
        if (daysOverdue > 0)
            return daysOverdue * 5;
        return 0;
    }

    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    String getTitle() {
        return title;
    }

    int getDaysOverdue() {
        return daysOverdue;
    }

    // Static because it calculates the total across many BookIssue objects.
    // fineAmount() is an instance method because each book has its own overdue days.
    static double totalFineCollected(Week3P1BookIssue[] issues) {
        double total = 0;

        for (Week3P1BookIssue book : issues) {
            total += book.fineAmount();
        }

        return total;
    }
}