package PracticeProblems.PPWeek11;

class Week11P5Node {
    int data;
    Week11P5Node next;

    Week11P5Node(int data) {
        this.data = data;
    }
}

public class Prob5 {
    static Week11P5Node reverse(Week11P5Node head) {
        Week11P5Node prev = null;
        Week11P5Node current = head;

        while (current != null) {
            Week11P5Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    static void reorderList(Week11P5Node head) {
        if (head == null || head.next == null)
            return;

        Week11P5Node slow = head;
        Week11P5Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Week11P5Node second = reverse(slow.next);
        slow.next = null;

        Week11P5Node first = head;

        while (second != null) {
            Week11P5Node next1 = first.next;
            Week11P5Node next2 = second.next;

            first.next = second;
            second.next = next1;

            first = next1;
            second = next2;
        }
    }

    static void printList(Week11P5Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Week11P5Node head = new Week11P5Node(1);
        head.next = new Week11P5Node(2);
        head.next.next = new Week11P5Node(3);
        head.next.next.next = new Week11P5Node(4);
        head.next.next.next.next = new Week11P5Node(5);

        reorderList(head);
        printList(head);
    }
}