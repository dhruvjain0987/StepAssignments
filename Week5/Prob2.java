package Week5;

public class Prob2 {
    public static void main(String[] args) {
        System.out.println(Week5P2AccessChecker.classifyAccess(
            "protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        System.out.println(Week5P2AccessChecker.classifyAccess(
            "protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));

        System.out.println(Week5P2AccessChecker.describeContext(
            "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
    }
}

class Week5P2AccessChecker {
    static String classifyAccess(String modifier, String context) {
        if (modifier.equals("public"))
            return "ALLOWED";

        if (modifier.equals("private"))
            return "DENIED";

        if (modifier.equals("default")) {
            if (context.equals("SAME_CLASS") ||
                context.equals("SAME_PACKAGE"))
                return "ALLOWED";
            return "DENIED";
        }

        if (modifier.equals("protected")) {
            if (context.equals("SAME_CLASS") ||
                context.equals("SAME_PACKAGE") ||
                context.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                return "ALLOWED";

            return "DENIED";
        }

        return "DENIED";
    }

    static String describeContext(String accessorContext) {
        String[] words = accessorContext.toLowerCase().split("_");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (result.length() > 0)
                result.append(" ");

            result.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1));
        }

        return result.toString();
    }
}