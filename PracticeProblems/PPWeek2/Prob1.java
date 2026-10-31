package PracticeProblems.PPWeek2;

import java.util.*;

public class Prob1 {
    static void countVowelsAndConsonants(String s) {
        int vowels = 0, consonants = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = Character.toLowerCase(s.charAt(i));

            if (ch == 'a' || ch == 'e' || ch == 'i' ||
                ch == 'o' || ch == 'u')
                vowels++;
            else if (ch >= 'a' && ch <= 'z')
                consonants++;
        }

        System.out.println("Vowels: " + vowels +
            " | Consonants: " + consonants);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String s = sc.nextLine();
        countVowelsAndConsonants(s);
    }
}
