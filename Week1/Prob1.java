package Week1;

public class Prob1 {
    public static void main(String[] args) {
        Week1P1SeatChecker checker = new Week1P1SeatChecker();

        int[] seats = {101, 102, 103, 102, 105};
        checker.checkDuplicateSeats(seats);
    }
}

class Week1P1SeatChecker {
    void checkDuplicateSeats(int[] seatNumbers) {
        boolean found = false;

        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    boolean alreadyPrinted = false;

                    for (int k = 0; k < i; k++) {
                        if (seatNumbers[k] == seatNumbers[i]) {
                            alreadyPrinted = true;
                            break;
                        }
                    }

                    if (!alreadyPrinted) {
                        System.out.println("Duplicate Seat Number Found: " +
                                           seatNumbers[i]);
                        found = true;
                    }
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("No Duplicate Seats Found");
        }
    }
}