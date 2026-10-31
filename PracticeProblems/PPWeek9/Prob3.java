package PracticeProblems.PPWeek9;
import java.util.*;

public class Prob3 {
    static int countPeriods(int[] transactions, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0, count = 0;

        for (int x : transactions) {
            sum += x;

            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};

        System.out.println(countPeriods(transactions, 7));
    }
}