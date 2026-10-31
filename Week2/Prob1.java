package Week2;

public class Prob1 {
    public static void main(String[] args) {
        Week2P1Validator validator = new Week2P1Validator();

        validator.checkPinLength("482");
        validator.checkPinLength("4820");
    }
}

class Week2P1Validator {
    void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }
}