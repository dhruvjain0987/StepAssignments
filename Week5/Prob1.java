package Week5;

public class Prob1 {
    public static void main(String[] args) {
        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(Week5P1Checker.summarizeByModifier(attempts));

        try {
            new Week5P1LibraryMember("LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}

class Week5P1Checker {
    static String classifyAccess(String modifier, String context) {
        if (modifier.equals("public"))
            return "ALLOWED";

        if (modifier.equals("private"))
            return context.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if (modifier.equals("default"))
            return context.equals("DIFFERENT_PACKAGE") ? "DENIED" : "ALLOWED";

        if (modifier.equals("protected"))
            return context.equals("DIFFERENT_PACKAGE") ? "DENIED" : "ALLOWED";

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        StringBuilder result = new StringBuilder();

        for (String modifier : modifiers) {
            int allowed = 0, denied = 0;

            for (String[] attempt : attempts) {
                if (attempt[0].equals(modifier)) {
                    if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED"))
                        allowed++;
                    else
                        denied++;
                }
            }

            if (result.length() > 0)
                result.append(" | ");

            result.append(modifier).append(": ")
                  .append(allowed).append(" allowed / ")
                  .append(denied).append(" denied");
        }

        return result.toString();
    }
}

class Week5P1LibraryMember {
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public Week5P1LibraryMember(String membershipId, String branchCode,
                                double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid membership ID");

        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}