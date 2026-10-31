package Week2;

public class Prob3 {
    public static void main(String[] args) {
        Week2P3Parser parser = new Week2P3Parser();

        parser.parseInventoryRecord("Wireless Mouse,WM-2201,150");
        parser.parseInventoryRecord("Wireless Mouse,150");
    }
}

class Week2P3Parser {
    void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",", -1);

        if (fields.length != 3) {
            System.out.println("Invalid Record");
        } else {
            System.out.println("Product: " + fields[0] +
                               " | SKU: " + fields[1] +
                               " | Qty: " + fields[2]);
        }
    }
}