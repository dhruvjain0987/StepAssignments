package PracticeProblems.PPWeek1;

import java.util.*;

public class Prob5 {
    static String reverseCustomerName(String name) {
        String reversed = "";

        for (int i = name.length() - 1; i >= 0; i--) {
            reversed += name.charAt(i);
        }

        return reversed;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.println("Original Name: " + name);
        System.out.println("Reversed Name: " + reverseCustomerName(name));
    }
}