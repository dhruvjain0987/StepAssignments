package PracticeProblems.PPWeek2;

import java.util.*;

public class Prob2 {
    static void parseStudentRecord(String csv) {
        String[] data = csv.split(",", -1);

        if (data.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Name: " + data[0].trim() +
            " | Roll No: " + data[1].trim() +
            " | Dept: " + data[2].trim());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter CSV record: ");
        String csv = sc.nextLine();
        parseStudentRecord(csv);
    }
}