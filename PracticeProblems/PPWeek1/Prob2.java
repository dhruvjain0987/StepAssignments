package PracticeProblems.PPWeek1;

import java.util.*;

public class Prob2 {
    static boolean isPalindromeIterative(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    static boolean isPalindromeRecursive(String s) {
        if (s.length() <= 1) return true;
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return isPalindromeRecursive(s.substring(1, s.length() - 1));
    }

    static boolean isPalindromeArrayReversal(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return s.equals(new String(arr));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String s = sc.nextLine();

        System.out.println("Iterative: " +
            (isPalindromeIterative(s) ? "Palindrome" : "Not Palindrome"));
        System.out.println("Recursive: " +
            (isPalindromeRecursive(s) ? "Palindrome" : "Not Palindrome"));
        System.out.println("Array Reversal: " +
            (isPalindromeArrayReversal(s) ? "Palindrome" : "Not Palindrome"));
    }
}