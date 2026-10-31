package Week8;
import java.util.*;

interface CreditPolicy {
    int getLimit();
}

class Regular implements CreditPolicy {
    public int getLimit() {
        return 24;
    }
}

class Honors implements CreditPolicy {
    public int getLimit() {
        return 28;
    }
}

class Exchange implements CreditPolicy {
    public int getLimit() {
        return 20;
    }
}

class ElectiveStudent {
    String name;
    int credits;
    CreditPolicy policy;

    ElectiveStudent(String name, int credits, CreditPolicy policy) {
        this.name = name;
        this.credits = credits;
        this.policy = policy;
    }
}

class Elective {
    String name;
    int credits;
    int capacity;

    ArrayList<ElectiveStudent> enrolled = new ArrayList<>();
    Queue<ElectiveStudent> waitlist = new LinkedList<>();

    Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    boolean contains(ElectiveStudent s) {
        return enrolled.contains(s) || waitlist.contains(s);
    }

    void enroll(ElectiveStudent s) {
        if (contains(s)) {
            System.out.println(s.name + " already enrolled or waitlisted.");
            return;
        }

        if (s.credits + credits > s.policy.getLimit()) {
            System.out.println("Enrollment failed: " + s.name +
                    " would exceed the " +
                    s.policy.getClass().getSimpleName() +
                    " credit limit (" + (s.credits + credits) +
                    "/" + s.policy.getLimit() + ").");
            return;
        }

        if (enrolled.size() < capacity) {
            enrolled.add(s);
            s.credits += credits;

            System.out.println(s.name + " enrolled in " + name +
                    " (credits: " + s.credits + "/" +
                    s.policy.getLimit() + ").");
        } else {
            waitlist.add(s);

            System.out.println(name + " is full.");
            System.out.println(s.name + " added to waitlist (position " +
                    waitlist.size() + ").");
        }
    }

    void drop(ElectiveStudent s) {
        if (!enrolled.contains(s)) {
            return;
        }

        enrolled.remove(s);
        s.credits -= credits;

        System.out.println(s.name + " dropped " + name +
                " (credits: " + s.credits + "/" +
                s.policy.getLimit() + ").");

        promote();
    }

    void promote() {
        int size = waitlist.size();

        while (size-- > 0 && enrolled.size() < capacity) {
            ElectiveStudent s = waitlist.poll();

            if (s.credits + credits <= s.policy.getLimit()) {
                enrolled.add(s);
                s.credits += credits;

                System.out.println(s.name +
                        " promoted from waitlist and enrolled in " +
                        name + " (credits: " + s.credits +
                        "/" + s.policy.getLimit() + ").");
                break;
            } else {
                System.out.println(s.name +
                        " skipped: credit limit exceeded.");
            }
        }
    }
}

public class Prob4 {
    public static void main(String[] args) {
        Elective e = new Elective("Cloud Computing", 4, 2);

        ElectiveStudent a = new ElectiveStudent(
                "Asha", 20, new Regular());

        ElectiveStudent r = new ElectiveStudent(
                "Ravi", 22, new Honors());

        ElectiveStudent n = new ElectiveStudent(
                "Neha", 12, new Exchange());

        ElectiveStudent k = new ElectiveStudent(
                "Kiran", 22, new Regular());

        e.enroll(a);
        e.enroll(r);
        e.enroll(n);
        e.enroll(k);

        e.drop(a);
    }
}