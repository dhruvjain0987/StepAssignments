package Week3;

public class Prob4 {
    public static void main(String[] args) {
        System.out.println("Broken version:");

        Week3P4BrokenMember b1 = new Week3P4BrokenMember("Aditi");
        Week3P4BrokenMember b2 = new Week3P4BrokenMember("Rohan");

        b1.printName();
        b2.printName();

        System.out.println("\nFixed version:");

        Week3P4LibraryMember m1 =
            new Week3P4LibraryMember("Aditi", 2);

        Week3P4LibraryMember m2 =
            new Week3P4LibraryMember("Rohan", 1);

        m1.printMemberCard();
        m2.printMemberCard();

        Week3P4LibraryMember.printTotalMembers();
    }
}

// Broken version: static fields are shared by every object.
// name, memberId and booksIssued must be different for each member.
class Week3P4BrokenMember {
    static String name;
    static String memberId;
    static int booksIssued;

    public Week3P4BrokenMember(String name) {
        Week3P4BrokenMember.name = name;
    }

    void printName() {
        System.out.println(name);
    }
}

class Week3P4LibraryMember {
    private String name;
    private String memberId;
    private int booksIssued;

    private static String libraryName = "Central Library";
    private static int memberCount = 1000;

    public Week3P4LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + memberCount;
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + (memberCount - 1000));
    }
}