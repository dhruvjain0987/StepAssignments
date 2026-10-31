package PracticeProblems.PPWeek7;
interface Week7P5Renewable {
    String renew();
}

interface Week7P5Reservable {
    String reserve();
}

abstract class Week7P5LibraryItem {
    private static int counter = 1000;
    private final String itemId;
    protected String title;

    Week7P5LibraryItem(String title) {
        this.title = title;
        itemId = "LIB-" + (++counter);
    }

    abstract int getLoanPeriodDays();

    String getItemId() {
        return itemId;
    }
}

class Week7P5Textbook extends Week7P5LibraryItem
    implements Week7P5Renewable, Week7P5Reservable {

    Week7P5Textbook(String title) {
        super(title);
    }

    int getLoanPeriodDays() {
        return 14;
    }

    public String renew() {
        return title + " renewed";
    }

    public String reserve() {
        return title + " reserved";
    }
}

class Week7P5Magazine extends Week7P5LibraryItem
    implements Week7P5Renewable {

    Week7P5Magazine(String title) {
        super(title);
    }

    int getLoanPeriodDays() {
        return 7;
    }

    public String renew() {
        return title + " renewed";
    }
}

class Week7P5DigitalPass implements Week7P5Renewable {
    String resourceName;

    Week7P5DigitalPass(String resourceName) {
        this.resourceName = resourceName;
    }

    public String renew() {
        return resourceName + " renewed";
    }
}

public class Prob5 {
    static void processCheckouts(Week7P5LibraryItem[] items) {
        for (Week7P5LibraryItem item : items)
            System.out.println(item.title + ": " +
                item.getLoanPeriodDays() + " days");
    }

    static String reserveIfSupported(Object o) {
        if (o instanceof Week7P5Reservable) {
            Week7P5Reservable r = (Week7P5Reservable) o;
            return r.reserve();
        }
        return "Reservation not supported";
    }

    public static void main(String[] args) {
        Week7P5Textbook t =
            new Week7P5Textbook("Java Fundamentals");

        Week7P5Magazine m =
            new Week7P5Magazine("Tech Monthly");

        Week7P5DigitalPass d =
            new Week7P5DigitalPass("E-Journal Access");

        System.out.println(t.getLoanPeriodDays());
        System.out.println(t.renew());
        System.out.println(t.reserve());

        System.out.println(reserveIfSupported(m));
        System.out.println(reserveIfSupported(d));

        Week7P5LibraryItem[] items = {t, m};
        processCheckouts(items);

        Week7P5LibraryItem ref = t; // Upcasting
        System.out.println(reserveIfSupported(ref));
    }
}