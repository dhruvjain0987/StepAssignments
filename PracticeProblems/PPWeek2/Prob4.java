package PracticeProblems.PPWeek2;

import java.util.*;

public class Prob4 {
    static String maskPhoneNumber(String phone) {
        if (phone.length() != 10) return "Invalid phone number";

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i)))
                return "Invalid phone number";
        }

        StringBuilder sb = new StringBuilder("XXXXXX");
        sb.append(phone.substring(6));
        sb.insert(6, "-");

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        System.out.println(maskPhoneNumber(phone));
    }
}