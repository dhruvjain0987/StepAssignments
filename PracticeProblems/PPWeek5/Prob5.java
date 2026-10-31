package PracticeProblems.PPWeek5;

import java.util.*;

final class Week5P5DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;
    static int processedCount;

    static {
        processedCount = 0;
    }

    Week5P5DischargeSummary(String patientId, String[] medicationCodes) {
        for (String code : medicationCodes) {
            if (code == null || !code.matches("MED-[A-Z]"))
                throw new IllegalArgumentException("Invalid medication code");
        }

        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }

    String[] getMedicationCodes() {
        return medicationCodes.clone();
    }

    Week5P5DischargeSummary withCorrectedMedication(int index,
                                                     String newCode) {
        if (newCode == null || !newCode.matches("MED-[A-Z]"))
            throw new IllegalArgumentException("Invalid medication code");

        String[] updated = medicationCodes.clone();
        updated[index] = newCode;

        return new Week5P5DischargeSummary(patientId, updated);
    }

    String getPatientId() {
        return patientId;
    }

    static String processNightlyBatch(
        Week5P5DischargeSummary[] summaries) {

        int processed = 0, skipped = 0, critical = 0, routine = 0;

        for (Week5P5DischargeSummary s : summaries) {
            if (s == null) {
                skipped++;
                continue;
            }

            processed++;
            if (s instanceof Week5P5CriticalCareDischargeSummary)
                critical++;
            else
                routine++;
        }

        processedCount += processed;

        return processed + " processed | " + skipped +
            " null skipped | " + critical + " critical-care | " +
            routine + " routine";
    }
}

final class Week5P5CriticalCareDischargeSummary
    extends Week5P5DischargeSummary {

    private final int icuDays;

    Week5P5CriticalCareDischargeSummary(String patientId,
                                        String[] medicationCodes,
                                        int icuDays) {
        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }
}

public class Prob5 {
    public static void main(String[] args) {
        Week5P5DischargeSummary d =
            new Week5P5DischargeSummary(
                "MT2026-0142",
                new String[]{"MED-A", "MED-B"});

        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";

        System.out.println(Arrays.toString(d.getMedicationCodes()));

        Week5P5DischargeSummary[] batch = {
            new Week5P5CriticalCareDischargeSummary(
                "MT001", new String[]{"MED-X"}, 4),
            null,
            new Week5P5DischargeSummary(
                "MT002", new String[]{"MED-Y"})
        };

        System.out.println(
            Week5P5DischargeSummary.processNightlyBatch(batch));
    }
}