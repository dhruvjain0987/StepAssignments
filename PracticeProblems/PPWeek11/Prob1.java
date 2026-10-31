package PracticeProblems.PPWeek11;


class Week11P1Node {
    int data;
    Week11P1Node next;

    Week11P1Node(int data) {
        this.data = data;
    }
}

public class Prob1 {
    static Week11P1Node removeNthFromEnd(Week11P1Node head, int n) {
        Week11P1Node dummy = new Week11P1Node(0);
        dummy.next = head;

        Week11P1Node fast = dummy;
        Week11P1Node slow = dummy;

        for (int i = 0; i <= n; i++)
            fast = fast.next;

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    static void printList(Week11P1Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Week11P1Node head = new Week11P1Node(1);
        head.next = new Week11P1Node(2);
        head.next.next = new Week11P1Node(3);
        head.next.next.next = new Week11P1Node(4);
        head.next.next.next.next = new Week11P1Node(5);

        head = removeNthFromEnd(head, 2);
        printList(head);

        Week11P1Node single = new Week11P1Node(1);
        single = removeNthFromEnd(single, 1);
        printList(single);
    }
}