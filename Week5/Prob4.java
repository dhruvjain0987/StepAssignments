package Week5;

public class Prob4 {
    public static void main(String[] args) {
        Week5P4LibraryMember m1 = new Week5P4LibraryMember("Priya Nair");
        System.out.println(m1.getMembershipId());

        Week5P4LibraryMember m2 =
            new Week5P4LibraryMember("LIB-8841", "Priya Nair");
        System.out.println(m2.getMembershipId());

        Week5P4LibraryMember m3 = new Week5P4LibraryMember();
        m3.setMembershipId("LIB-8841");
        m3.setMembershipId("FAKE-0000");

        System.out.println(m3.getMembershipId());
    }
}

class Week5P4LibraryMember {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;

    public Week5P4LibraryMember() {
        this(null, null);
    }

    public Week5P4LibraryMember(String name) {
        this(null, name);
    }

    public Week5P4LibraryMember(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (membershipId == null)
            membershipId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        if (answer != null)
            securityAnswerHash = Integer.toHexString(answer.hashCode());
    }
}