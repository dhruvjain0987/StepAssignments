package PracticeProblems.PPWeek9;

public class Prob4 {
    static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] < target)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }

    static int countInBand(int[] scores, int low, int high) {
        return lowerBound(scores, high + 1) - lowerBound(scores, low);
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};

        System.out.println(countInBand(scores, 42, 58));
        System.out.println(countInBand(scores, 90, 100));
    }
}