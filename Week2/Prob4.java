package Week2;

public class Prob4 {
    public static void main(String[] args) {
        Week2P4ISBNValidator validator = new Week2P4ISBNValidator();

        String code1 = validator.normalizeCode(" pen2026004251 ");
        System.out.println(validator.validateAndFormat(code1));

        String code2 = validator.normalizeCode("12N2026004251");
        System.out.println(validator.validateAndFormat(code2));
    }
}

class Week2P4ISBNValidator {
    String normalizeCode(String raw) {
        raw = raw.trim();

        if (raw.length() < 3) {
            return raw.toUpperCase();
        }

        String publisher = raw.substring(0, 3).toUpperCase();
        String remaining = raw.substring(3);

        return publisher + remaining;
    }

    String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        StringBuilder result = new StringBuilder();

        result.append("[")
              .append(code.substring(0, 3))
              .append("] YEAR: ")
              .append(code.substring(3, 7))
              .append(" | CATALOG: ")
              .append(code.substring(7, 13));

        return result.toString();
    }
}