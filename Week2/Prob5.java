package Week2;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Prob5 {
    public static void main(String[] args) {
        Week2P5FrequencyCounter counter =
            new Week2P5FrequencyCounter();

        counter.printFilteredWordFrequency(
            "The mentor was great, the session was great and clear."
        );
    }
}

class Week2P5FrequencyCounter {
    void printFilteredWordFrequency(String feedback) {
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        feedback = feedback.toLowerCase();
        feedback = feedback.replace(".", "");
        feedback = feedback.replace(",", "");

        String[] words = feedback.split("\\s+");

        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {
            boolean isStopWord = false;

            for (String stop : stopWords) {
                if (word.equals(stop)) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord && !word.isEmpty()) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }

        ArrayList<Map.Entry<String, Integer>> list =
            new ArrayList<>(frequency.entrySet());

        Collections.sort(list,
            new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> a,
                                   Map.Entry<String, Integer> b) {
                    return b.getValue() - a.getValue();
                }
            });

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}