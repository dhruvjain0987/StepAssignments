package PracticeProblems.PPWeek5;

class Week5P2AccessRuleEngine {
    static String classifyAccess(String modifier, String context) {
        if (modifier.equals("public")) return "ALLOWED";

        if (context.equals("SAME_CLASS")) return "ALLOWED";

        if (modifier.equals("private")) return "DENIED";

        if (context.equals("SAME_PACKAGE")) return "ALLOWED";

        if (modifier.equals("protected") &&
            context.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
            return "ALLOWED";

        return "DENIED";
    }

    static String describeContext(String context) {
        String[] words = context.toLowerCase().split("_");
        String result = "";

        for (String word : words) {
            result += Character.toUpperCase(word.charAt(0))
                + word.substring(1) + " ";
        }

        return result.trim();
    }
}

public class Prob2 {
    public static void main(String[] args) {
        System.out.println(
            Week5P2AccessRuleEngine.classifyAccess(
                "protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        System.out.println(
            Week5P2AccessRuleEngine.classifyAccess(
                "protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));

        System.out.println(
            Week5P2AccessRuleEngine.describeContext(
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}