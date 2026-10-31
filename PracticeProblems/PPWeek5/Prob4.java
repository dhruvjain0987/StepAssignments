package PracticeProblems.PPWeek5;

class Week5P4PatientProfile {
    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPinHash;

    public Week5P4PatientProfile() {
        this(null, null);
    }

    public Week5P4PatientProfile(String name) {
        this(null, name);
    }

    public Week5P4PatientProfile(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {
        if (patientId == null)
            patientId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {
        if (pin != null && pin.matches("[0-9]{4,6}"))
            lockerPinHash = Integer.toString(pin.hashCode());
    }
}

public class Prob4 {
    public static void main(String[] args) {
        Week5P4PatientProfile p =
            new Week5P4PatientProfile("Arjun Iyer");

        System.out.println(p.getPatientId());

        Week5P4PatientProfile p2 =
            new Week5P4PatientProfile("MT2026-0142", "Arjun Iyer");

        System.out.println(p2.getPatientId());

        Week5P4PatientProfile p3 = new Week5P4PatientProfile();
        p3.setPatientId("MT2026-0142");
        p3.setPatientId("HACKED-0000");

        System.out.println(p3.getPatientId());

        p3.setLockerPin("123456");
    }
}