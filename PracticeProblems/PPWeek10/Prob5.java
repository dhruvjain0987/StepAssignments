package PracticeProblems.PPWeek10;
import java.util.*;

interface Week10P5Reservable {
    void reserve(String member);
}

interface Week10P5Downloadable {
    void download();
}

class Week10P5Resource {
    String id;

    Week10P5Resource(String id) {
        this.id = id;
    }
}

class Week10P5Book extends Week10P5Resource implements Week10P5Reservable {
    Week10P5Book(String id) { super(id); }

    public void reserve(String member) {
        System.out.println(id + " reserved for " + member);
    }
}

class Week10P5EBook extends Week10P5Resource implements Week10P5Downloadable {
    Week10P5EBook(String id) { super(id); }

    public void download() {
        System.out.println(id + " downloaded");
    }
}

public class Prob5 {
    static ArrayList<Week10P5Resource> resources = new ArrayList<>();

    static void add(Week10P5Resource r) {
        for (Week10P5Resource x : resources) {
            if (x.id.equals(r.id)) {
                System.out.println("duplicate rejected");
                return;
            }
        }

        resources.add(r);
        resources.sort(Comparator.comparing(x -> x.id));
        System.out.println(r.id + " added");
    }

    static Week10P5Resource find(String id) {
        for (Week10P5Resource r : resources)
            if (r.id.equals(id))
                return r;

        return null;
    }

    public static void main(String[] args) {
        add(new Week10P5Book("B1"));
        add(new Week10P5Book("B1"));
        add(new Week10P5EBook("E1"));

        Week10P5Resource book = find("B1");
        Week10P5Resource ebook = find("E1");

        if (book instanceof Week10P5Reservable)
            ((Week10P5Reservable) book).reserve("M1");

        if (book instanceof Week10P5Downloadable)
            ((Week10P5Downloadable) book).download();
        else
            System.out.println("B1 rejected: download unsupported");

        if (ebook instanceof Week10P5Downloadable)
            ((Week10P5Downloadable) ebook).download();

        System.out.println("E1 found at index " + resources.indexOf(ebook));
    }
}