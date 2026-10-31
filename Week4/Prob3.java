package Week4;

public class Prob3 {
    public static void main(String[] args) {
        Week4P3Canteen[] canteens = {
            new Week4P3Canteen("HB3-C", "Spice Junction", 3),
            new Week4P3Canteen("hb1-c", "Grand Mess", 5),
            new Week4P3Canteen("HB2-C", "Southern Treats")
        };

        Week4P3Canteen[] result =
            Week4P3Canteen.rankCanteens(canteens);

        for (Week4P3Canteen c : result) {
            System.out.println(c.getCanteenCode());
        }
    }
}

class Week4P3Canteen {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Week4P3Canteen(String canteenCode,
                          String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Week4P3Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    String getCanteenCode() {
        return canteenCode;
    }

    int compareTo(Week4P3Canteen other) {
        if (this.trustScore != other.trustScore) {
            return other.trustScore - this.trustScore;
        }

        int codeResult =
            this.canteenCode.compareToIgnoreCase(other.canteenCode);

        if (codeResult != 0)
            return codeResult;

        return this.canteenName.length() - other.canteenName.length();
    }

    static Week4P3Canteen[] rankCanteens(Week4P3Canteen[] canteens) {
        Week4P3Canteen[] result = canteens.clone();

        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result[j].compareTo(result[j + 1]) > 0) {
                    Week4P3Canteen temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }
}