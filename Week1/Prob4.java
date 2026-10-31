package Week1;

public class Prob4 {
    public static void main(String[] args) {
        Week1P4InventoryAnalyzer analyzer = new Week1P4InventoryAnalyzer();

        int[] sectionA = {20, 15, 30};
        int[] sectionB = {25, 10, 30};

        analyzer.analyzeInventory(sectionA, sectionB);
    }
}

class Week1P4InventoryAnalyzer {
    void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0;
        int totalB = 0;

        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            totalB += sectionB[i];
        }

        String status;

        if (totalA == totalB) {
            status = "Balanced";
        } else {
            status = "Not Balanced";
        }

        int max = sectionA[0];
        String section = "Section A";
        int index = 0;

        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > max) {
                max = sectionA[i];
                section = "Section A";
                index = i;
            }

            if (sectionB[i] > max) {
                max = sectionB[i];
                section = "Section B";
                index = i;
            }
        }

        System.out.println("Section A Total: " + totalA +
                           " | Section B Total: " + totalB +
                           " | Status: " + status +
                           " | Highest Quantity: " + max +
                           " (" + section + ", Item " + (index + 1) + ")");
    }
}