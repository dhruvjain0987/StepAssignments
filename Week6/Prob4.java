package Week6;

public class Prob4 {
    public static void main(String[] args) {
        Week6P4RaceEntry runner =
            new Week6P4RunnerEntry("BIB2001", 90, "Open 10K");

        Week6P4RaceEntry relay =
            new Week6P4RelayTeamEntry("BIB4001", 300, 4);

        Week6P4RaceEntry[] entries = {runner, relay};

        System.out.println(Week6P4RaceEntry.announceAll(entries));
    }
}

class Week6P4RaceEntry {
    protected String bibNumber;
    protected double balanceDue;

    public Week6P4RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }

        this.bibNumber = bibNumber;
        this.balanceDue = entryFee;
    }

    String announce() {
        return "Race Entry | Bib: " + bibNumber +
               " | Balance: " + balanceDue;
    }

    static String announceAll(Week6P4RaceEntry[] entries) {
        StringBuilder result = new StringBuilder();

        for (Week6P4RaceEntry entry : entries) {
            result.append(entry.announce());

            if (entry instanceof Week6P4RelayTeamEntry) {
                Week6P4RelayTeamEntry relay =
                    (Week6P4RelayTeamEntry) entry;

                result.append(" [Team size via downcast: ")
                      .append(relay.getTeamSize())
                      .append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }
}

class Week6P4RunnerEntry extends Week6P4RaceEntry {
    private String category;

    public Week6P4RunnerEntry(String bibNumber, double entryFee, String category) {
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

class Week6P4RelayTeamEntry extends Week6P4RaceEntry {
    private int teamSize;

    public Week6P4RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    int getTeamSize() {
        return teamSize;
    }

    @Override
    String announce() {
        return "Relay Team | Bib: " + bibNumber +
               " | Team Size: " + teamSize +
               " | Balance: " + balanceDue;
    }
}