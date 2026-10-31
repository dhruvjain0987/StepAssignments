package PracticeProblems.PPWeek3;

class Week3P3HostelRoom {
    String roomNo;
    int beds, occupied;

    Week3P3HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot(String name) {
        if (occupied < beds) {
            occupied++;
            System.out.println(name + " allotted to room " + roomNo);
        }
    }
}

public class Prob3 {
    static Week3P3HostelRoom findAvailableRoom(Week3P3HostelRoom[] rooms) {
        for (Week3P3HostelRoom r : rooms) {
            if (r != null && r.occupied < r.beds)
                return r;
        }
        return null;
    }

    static void safeAllot(Week3P3HostelRoom[] rooms, String studentName) {
        Week3P3HostelRoom room = findAvailableRoom(rooms);

        if (room == null)
            System.out.println("No rooms available for " + studentName);
        else
            room.allot(studentName);
    }

    public static void main(String[] args) {
        Week3P3HostelRoom[] rooms1 = {
            new Week3P3HostelRoom("C-214", 3, 2),
            new Week3P3HostelRoom("C-507", 2, 2)
        };

        safeAllot(rooms1, "Divya");

        Week3P3HostelRoom[] rooms2 = {
            new Week3P3HostelRoom("C-214", 3, 3),
            new Week3P3HostelRoom("C-507", 2, 2)
        };

        safeAllot(rooms2, "Divya");
    }
}