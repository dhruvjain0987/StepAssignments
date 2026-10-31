package Week5;

public class Prob3 {
    public static void main(String[] args) {
        Week5P3BookInventory b = new Week5P3BookInventory(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();

        System.out.println(b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();

        System.out.println(b.getCopiesAvailable());
    }
}

class Week5P3BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    Week5P3BookInventory(int copiesTotal) {
        if (copiesTotal <= 0)
            throw new IllegalArgumentException("Invalid inventory");

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    void checkOut() {
        if (copiesAvailable > 0)
            copiesAvailable--;
    }

    void checkIn() {
        if (copiesAvailable < copiesTotal)
            copiesAvailable++;
    }

    int getCopiesAvailable() {
        return copiesAvailable;
    }
}