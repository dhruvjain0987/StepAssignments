package PracticeProblems.PPWeek10;

import java.util.*;

class Week10P2Customer {
    int id;
    String name;

    Week10P2Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Week10P2Customer)) return false;

        Week10P2Customer c = (Week10P2Customer) obj;
        return id == c.id && name.equals(c.name);
    }

    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class Prob2 {
    public static void main(String[] args) {
        Set<Week10P2Customer> customers = new HashSet<>();

        System.out.println(customers.add(new Week10P2Customer(101, "Asha")));
        System.out.println(customers.add(new Week10P2Customer(101, "Asha")));
        System.out.println(customers.add(new Week10P2Customer(102, "Ravi")));

        System.out.println("Unique count: " + customers.size());
        System.out.println("Contains: " +
            customers.contains(new Week10P2Customer(101, "Asha")));
    }
}