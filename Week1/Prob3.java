package Week1;

public class Prob3 {
    public static void main(String[] args) {
        Week1P3StreakAnalyzer analyzer = new Week1P3StreakAnalyzer();

        analyzer.findLongestStreak("RRGGGYRR");
        analyzer.findLongestStreak("RRRRYYGG");
    }
}

class Week1P3StreakAnalyzer {
    void findLongestStreak(String signalLog) {
        int currentCount = 1;
        int maxCount = 1;
        char maxColor = signalLog.charAt(0);

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                currentCount++;
            } else {
                currentCount = 1;
            }

            if (currentCount > maxCount) {
                maxCount = currentCount;
                maxColor = signalLog.charAt(i);
            }
        }

        System.out.println("Longest Streak: '" + maxColor +
                           "' repeated " + maxCount + " times");
    }
}