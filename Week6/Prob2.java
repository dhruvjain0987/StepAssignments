package Week6;


public class Prob2 {
    public static void main(String[] args) {
        Week6P2RaceEntry runner =
            new Week6P2RunnerEntry("BIB2001", 80, "Open 10K");

        Week6P2RaceEntry elite =
            new Week6P2EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);

        Week6P2RaceEntry relay =
            new Week6P2RelayTeamEntry("BIB4001", 300, 4);

        System.out.println(runner.announce());
        System.out.println(elite.announce());
        System.out.println(relay.announce());

        System.out.println(Week6P2RaceEntry.classifyGeneration(elite));
        System.out.println(Week6P2RaceEntry.classifyGeneration(relay));

        Week6P2RaceEntry[] entries = {runner, elite, relay};

        System.out.println(Week6P2RaceEntry.getTotalBalanceDue(entries));
    }
}

class Week6P2RaceEntry {
    protected String bibNumber;
    protected double balanceDue;

    public Week6P2RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }

        this.bibNumber = bibNumber;
        this.balanceDue = entryFee;
    }

    double getBalanceDue() {
        return balanceDue;
    }

    String announce() {
        return "Race Entry | Bib: " + bibNumber +
               " | Balance: " + balanceDue;
    }

    static String classifyGeneration(Week6P2RaceEntry entry) {
        if (entry instanceof Week6P2EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        } else if (entry instanceof Week6P2RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        } else {
            return "Base Race Entry";
        }
    }

    static double getTotalBalanceDue(Week6P2RaceEntry[] entries) {
        double total = 0;

        for (Week6P2RaceEntry entry : entries) {
            total += entry.getBalanceDue();
        }

        return total;
    }
}

class Week6P2RunnerEntry extends Week6P2RaceEntry {
    protected String category;

    public Week6P2RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    String announce() {
        return "Runner Entry | Bib: " + bibNumber +
               " | Category: " + category +
               " | Balance: " + balanceDue;
    }
}

class Week6P2EliteRunnerEntry extends Week6P2RunnerEntry {
    private double sponsorBonus;

    public Week6P2EliteRunnerEntry(String bibNumber, double entryFee,
                                   String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    String announce() {
        return "Elite Runner | Bib: " + bibNumber +
               " | Category: " + category +
               " | Sponsor Bonus: " + sponsorBonus +
               " | Balance: " + balanceDue;
    }
}

class Week6P2RelayTeamEntry extends Week6P2RaceEntry {
    private int teamSize;

    public Week6P2RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    @Override
    String announce() {
        return "Relay Team | Bib: " + bibNumber +
               " | Team Size: " + teamSize +
               " | Balance: " + balanceDue;
    }
}