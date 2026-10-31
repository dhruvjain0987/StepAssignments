package PracticeProblems.PPWeek10;

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

    static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] <= target)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};

        int low = lowerBound(prices, 150);
        int high = upperBound(prices, 300);

        System.out.println("Lower bound index " + low);
        System.out.println("Upper bound index " + high);
        System.out.println("Count " + (high - low));
    }
}