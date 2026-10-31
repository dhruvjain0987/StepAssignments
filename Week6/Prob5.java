package Week6;

public class Prob5 {
    public static void main(String[] args) {
        Week6P5RaceEntry elite =
            new Week6P5EliteRunnerEntry("BIB3001", 150, "Elite", 500);

        Week6P5RaceEntry relay =
            new Week6P5RelayTeamEntry("BIB4001", 300, 4);

        System.out.println(Week6P5RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(Week6P5RaceEntry.isValidDiscountCode("M12A"));
        System.out.println(Week6P5RaceEntry.isValidDiscountCode("X123A"));

        elite.pay(10, "UPI");

        Week6P5RaceEntry[] entries = {elite, null, relay};

        System.out.println(Week6P5RaceEntry.settleNight(entries));
        System.out.println(Week6P5RaceEntry.getBibCounter());
    }
}

class Week6P5RaceEntry {
    protected String bibNumber;
    protected double balanceDue;

    private static int bibCounter = 0;
    private final String entryCode;

    public Week6P5RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }

        this.bibNumber = bibNumber;
        this.balanceDue = entryFee;

        bibCounter++;
        entryCode = "ENTRY" + bibCounter;
    }

    void pay(double amount) {
        balanceDue -= amount;
    }

    void pay(double amount, String mode) {
        pay(amount);
        System.out.println("Paying via " + mode);
    }

    double getBalanceDue() {
        return balanceDue;
    }

    static int getBibCounter() {
        return bibCounter;
    }

    static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }

        return code.charAt(0) == 'M'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isDigit(code.charAt(3))
                && Character.isUpperCase(code.charAt(4));
    }

    static String settleNight(Week6P5RaceEntry[] entries) {
        int processed = 0;
        int nullSkipped = 0;
        int relayCount = 0;
        int individualCount = 0;

        for (Week6P5RaceEntry entry : entries) {
            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof Week6P5RelayTeamEntry) {
                relayCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               relayCount + " relay | " +
               individualCount + " individual";
    }
}

class Week6P5RunnerEntry extends Week6P5RaceEntry {
    private String category;

    public Week6P5RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
}

class Week6P5EliteRunnerEntry extends Week6P5RunnerEntry {
    private double sponsorBonus;

    public Week6P5EliteRunnerEntry(String bibNumber, double entryFee,
                                   String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }
}

class Week6P5RelayTeamEntry extends Week6P5RaceEntry {
    private int teamSize;

    public Week6P5RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }
}