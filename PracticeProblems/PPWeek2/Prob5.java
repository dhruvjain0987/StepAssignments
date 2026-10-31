package PracticeProblems.PPWeek2;

import java.util.*;

public class Prob5 {
    static String normalizeReference(String raw) {
        raw = raw.trim();

        if (raw.length() < 3) return raw.toUpperCase();

        return raw.substring(0, 3).toUpperCase() + raw.substring(3);
    }

    static String validateAndFormat(String reference) {
        if (reference.length() != 14)
            return "Invalid: wrong length";

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i)))
                return "Invalid: bank code must be 3 letters";
        }

        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i)))
                return "Invalid: body must contain only digits";
        }

        String bank = reference.substring(0, 3);
        String date = reference.substring(3, 9);
        String sequence = reference.substring(9, 14);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bank).append("] DATE: ");
        sb.append(date.substring(0, 2)).append("/");
        sb.append(date.substring(2, 4)).append("/");
        sb.append(date.substring(4, 6));
        sb.append(" | SEQ: ").append(sequence);

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter transaction reference: ");
        String raw = sc.nextLine();

        String normalized = normalizeReference(raw);
        System.out.println(validateAndFormat(normalized));
    }
}