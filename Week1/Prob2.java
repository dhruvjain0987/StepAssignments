package Week1;

public class Prob2 {
    public static void main(String[] args) {
        Week1P2TypingChecker checker = new Week1P2TypingChecker();

        checker.checkTypingAccuracy("hello world", "hello worlt");
        checker.checkTypingAccuracy("coding", "coding");
    }
}

class Week1P2TypingChecker {
    void checkTypingAccuracy(String original, String typed) {
        int matched = 0;
        int firstMismatch = -1;

        int length = Math.min(original.length(), typed.length());

        for (int i = 0; i < length; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        double accuracy = (matched * 100.0) / original.length();

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | ",
                          matched, original.length(), accuracy);

        if (firstMismatch == -1 && original.length() == typed.length()) {
            System.out.println("No Mismatches");
        } else if (firstMismatch != -1) {
            System.out.println("First Mismatch at position " +
                (firstMismatch + 1) + " ('" +
                original.charAt(firstMismatch) + "' vs '" +
                typed.charAt(firstMismatch) + "')");
        } else {
            System.out.println("First Mismatch at position " +
                (length + 1) + " (length differs)");
        }
    }
}