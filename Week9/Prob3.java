package Week9;
import java.util.*;

public class Prob3 {
    public static int countPeriods(int[] transactions, int k) {
        HashMap<Long, Integer> map = new HashMap<>();

        long sum = 0;
        int count = 0;

        map.put(0L, 1);

        for (int num : transactions) {
            sum += num;

            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};

        System.out.println(countPeriods(transactions, 7));

        int[] transactions2 = {1, 2, 3};
        System.out.println(countPeriods(transactions2, 10));
    }
}