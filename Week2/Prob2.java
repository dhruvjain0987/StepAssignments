package Week2;

public class Prob2 {
    public static void main(String[] args) {
        Week2P2Encoder encoder = new Week2P2Encoder();

        System.out.println(encoder.reverseEachWord("hello club"));
    }
}

class Week2P2Encoder {
    String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        String result = "";

        for (int i = 0; i < words.length; i++) {
            StringBuilder reversed = new StringBuilder();

            for (int j = words[i].length() - 1; j >= 0; j--) {
                reversed.append(words[i].charAt(j));
            }

            result += reversed.toString();

            if (i < words.length - 1) {
                result += " ";
            }
        }

        return result;
    }
}