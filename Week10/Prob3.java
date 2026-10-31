package Week10;

class TicketNode {
    int data;
    TicketNode next;

    TicketNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Prob3 {

    public static TicketNode mergeSorted(TicketNode a, TicketNode b) {
        TicketNode dummy = new TicketNode(0);
        TicketNode tail = dummy;

        while (a != null && b != null) {
            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        if (a != null) {
            tail.next = a;
        } else {
            tail.next = b;
        }

        return dummy.next;
    }

    public static void printList(TicketNode head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TicketNode a = new TicketNode(3);
        a.next = new TicketNode(8);
        a.next.next = new TicketNode(15);

        TicketNode b = new TicketNode(5);
        b.next = new TicketNode(8);
        b.next.next = new TicketNode(12);
        b.next.next.next = new TicketNode(20);

        TicketNode result = mergeSorted(a, b);
        printList(result);
    }
}