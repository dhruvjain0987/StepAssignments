package PracticeProblems.PPWeek11;
class Week11P2Node {
    int data;
    Week11P2Node next;

    Week11P2Node(int data) {
        this.data = data;
    }
}

public class Prob2 {
    static Week11P2Node reverse(Week11P2Node head) {
        Week11P2Node prev = null;
        Week11P2Node current = head;

        while (current != null) {
            Week11P2Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    static boolean isPalindrome(Week11P2Node head) {
        if (head == null || head.next == null)
            return true;

        Week11P2Node slow = head;
        Week11P2Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Week11P2Node second = reverse(slow.next);
        Week11P2Node first = head;

        while (second != null) {
            if (first.data != second.data)
                return false;

            first = first.next;
            second = second.next;
        }

        return true;
    }

    public static void main(String[] args) {
        Week11P2Node head = new Week11P2Node(1);
        head.next = new Week11P2Node(2);
        head.next.next = new Week11P2Node(2);
        head.next.next.next = new Week11P2Node(1);

        System.out.println(isPalindrome(head));

        Week11P2Node head2 = new Week11P2Node(1);
        head2.next = new Week11P2Node(2);
        head2.next.next = new Week11P2Node(3);

        System.out.println(isPalindrome(head2));
    }
}