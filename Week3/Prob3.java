package Week3;

public class Prob3 {
    public static void main(String[] args) {
        Week3P3ParkingSlot[] slots1 = {
            new Week3P3ParkingSlot("A1", 4, 3),
            new Week3P3ParkingSlot("A2", 5, 5)
        };

        Week3P3ParkingSlot.safeAllot(slots1, "TN09AB1234");

        Week3P3ParkingSlot[] slots2 = {
            new Week3P3ParkingSlot("A1", 4, 4),
            new Week3P3ParkingSlot("A2", 5, 5)
        };

        Week3P3ParkingSlot.safeAllot(slots2, "TN09AB1234");
    }
}

class Week3P3ParkingSlot {
    private String slotNo;
    private int capacity;
    private int occupiedCount;

    public Week3P3ParkingSlot(String slotNo, int capacity,
                              int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to slot " + slotNo);
        }
    }

    static Week3P3ParkingSlot findAvailableSlot(
        Week3P3ParkingSlot[] slots) {

        for (Week3P3ParkingSlot slot : slots) {
            if (slot != null && slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }

        return null;
    }

    static void safeAllot(Week3P3ParkingSlot[] slots, String vehicleNo) {
        Week3P3ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null) {
            slot.allot(vehicleNo);
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }
}