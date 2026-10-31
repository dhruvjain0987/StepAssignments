package PracticeProblems.PPWeek1;

import java.util.*;

public class Prob4 {
    static char findFirstNonRepeatingChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.get(ch) == 1) return ch;
        }

        return '\0';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String s = sc.nextLine();

        char result = findFirstNonRepeatingChar(s);

        if (result == '\0')
            System.out.println("No Non-Repeating Character Found");
        else
            System.out.println("First Non-Repeating Character: '" + result + "'");
    }
}
