package Week5;

import java.util.Arrays;

public class Prob5 {
    public static void main(String[] args) {
        Week5P5LoanReceipt r = new Week5P5LoanReceipt(
            "LIB-8841", new String[]{"BK-100", "BK-101"}
        );

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        Week5P5LoanReceipt corrected =
            r.withCorrectedBookId(0, "BK-200");

        System.out.println(corrected.getBookIds()[0]);

        Week5P5LoanReceipt[] receipts = {
            new Week5P5ReferenceOnlyLoanReceipt(
                "LIB-001", new String[]{"BK-200"}, "Reading Room 3"
            ),
            null,
            new Week5P5LoanReceipt(
                "LIB-002", new String[]{"BK-201"}
            )
        };

        System.out.println(
            Week5P5LoanReceipt.processNightlyCirculation(receipts)
        );
    }
}

class Week5P5LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    private static int totalReceipts;

    static {
        totalReceipts = 0;
    }

    public Week5P5LoanReceipt(String memberId, String[] bookIds) {
        if (bookIds == null)
            throw new IllegalArgumentException("Invalid book IDs");

        for (String id : bookIds) {
            if (id == null || !id.matches("BK-[0-9]{3}"))
                throw new IllegalArgumentException("Invalid book ID");
        }

        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
        totalReceipts++;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public Week5P5LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length)
            throw new IndexOutOfBoundsException();

        if (newId == null || !newId.matches("BK-[0-9]{3}"))
            throw new IllegalArgumentException("Invalid book ID");

        String[] updated = getBookIds();
        updated[index] = newId;

        return new Week5P5LoanReceipt(memberId, updated);
    }

    static String processNightlyCirculation(Week5P5LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (Week5P5LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof Week5P5ReferenceOnlyLoanReceipt)
                referenceOnly++;
            else
                regular++;
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               referenceOnly + " reference-only | " +
               regular + " regular";
    }
}

class Week5P5ReferenceOnlyLoanReceipt extends Week5P5LoanReceipt {
    private final String roomNumber;

    public Week5P5ReferenceOnlyLoanReceipt(
        String memberId, String[] bookIds, String roomNumber
    ) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}