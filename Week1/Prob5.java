package Week1;

public class Prob5 {
    public static void main(String[] args) {
        Week1P5WordProfiler profiler = new Week1P5WordProfiler();

        profiler.classifyWordLengths(
            "This movie was absolutely fantastic and thrilling"
        );
    }
}

class Week1P5WordProfiler {
    void classifyWordLengths(String review) {
        String[] words = review.split("\\s+");

        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String word : words) {
            int length = word.length();

            if (length >= 1 && length <= 4) {
                shortCount++;
            } else if (length >= 5 && length <= 8) {
                mediumCount++;
            } else {
                longCount++;
            }
        }

        System.out.println("Short: " + shortCount +
                           " | Medium: " + mediumCount +
                           " | Long: " + longCount);
    }
}