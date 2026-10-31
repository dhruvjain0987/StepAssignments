package PracticeProblems.PPWeek5;


class Week5P1AccessRuleEngine {
    static String classifyAccess(String modifier, String context) {
        if (modifier.equals("public")) return "ALLOWED";

        if (context.equals("SAME_CLASS")) return "ALLOWED";

        if (modifier.equals("private")) return "DENIED";

        if (context.equals("SAME_PACKAGE")) return "ALLOWED";

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {
        int allowed = 0, denied = 0;

        for (String[] a : attempts) {
            if (classifyAccess(a[0], a[1]).equals("ALLOWED"))
                allowed++;
            else
                denied++;
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }
}

class Week5P1PatientRecord {
    private String patientId;
    String wardCode;
    protected double vitalsScore;
    public String facilityName;

    Week5P1PatientRecord(String patientId, String wardCode,
                         double vitalsScore, String facilityName) {
        if (patientId == null || patientId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid patient ID");

        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}

public class Prob1 {
    public static void main(String[] args) {
        System.out.println(
            Week5P1AccessRuleEngine.classifyAccess("private", "SAME_CLASS"));

        System.out.println(
            Week5P1AccessRuleEngine.classifyAccess("default", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
            {"protected", "SAME_PACKAGE"},
            {"protected", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
            Week5P1AccessRuleEngine.summarizeBatch(attempts));

        try {
            Week5P1PatientRecord p =
                new Week5P1PatientRecord("MT9", "W3", 98.2, "MediTrack");
        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected");
        }
    }
}