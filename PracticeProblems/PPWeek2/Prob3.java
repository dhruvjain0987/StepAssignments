package PracticeProblems.PPWeek2;

import java.util.*;

public class Prob3 {
    static String validateFileExtension(String filename) {
        int dot = filename.lastIndexOf('.');

        if (dot == -1) return "Rejected — invalid file type";

        String ext = filename.substring(dot + 1);

        if (ext.equalsIgnoreCase("pdf") ||
            ext.equalsIgnoreCase("docx") ||
            ext.equalsIgnoreCase("zip"))
            return "Accepted";

        return "Rejected — invalid file type";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = sc.nextLine();

        System.out.println(validateFileExtension(filename));
    }
}