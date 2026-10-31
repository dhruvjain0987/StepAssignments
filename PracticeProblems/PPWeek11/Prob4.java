package PracticeProblems.PPWeek11;


class Week11P4Node {
    int data;
    Week11P4Node next;

    Week11P4Node(int data) {
        this.data = data;
    }
}

public class Prob4 {
    static int length(Week11P4Node head) {
        int count = 0;

        while (head != null) {
            count++;
            head = head.next;
        }

        return count;
    }

    static Week11P4Node findIntersection(Week11P4Node a, Week11P4Node b) {
        int lenA = length(a);
        int lenB = length(b);

        while (lenA > lenB) {
            a = a.next;
            lenA--;
        }

        while (lenB > lenA) {
            b = b.next;
            lenB--;
        }

        while (a != null && b != null) {
            if (a == b)
                return a;

            a = a.next;
            b = b.next;
        }

        return null;
    }

    public static void main(String[] args) {
        Week11P4Node shared = new Week11P4Node(8);
        shared.next = new Week11P4Node(4);
        shared.next.next = new Week11P4Node(5);

        Week11P4Node a = new Week11P4Node(4);
        a.next = new Week11P4Node(1);
        a.next.next = shared;

        Week11P4Node b = new Week11P4Node(5);
        b.next = new Week11P4Node(6);
        b.next.next = new Week11P4Node(1);
        b.next.next.next = shared;

        Week11P4Node result = findIntersection(a, b);

        if (result != null)
            System.out.println("Node with value " + result.data);
        else
            System.out.println("null");
    }
}