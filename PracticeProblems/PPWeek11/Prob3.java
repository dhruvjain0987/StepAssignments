package PracticeProblems.PPWeek11;

class Week11P3Node {
    int data;
    Week11P3Node next;

    Week11P3Node(int data) {
        this.data = data;
    }
}

public class Prob3 {
    static Week11P3Node addNumbers(Week11P3Node a, Week11P3Node b) {
        Week11P3Node dummy = new Week11P3Node(0);
        Week11P3Node current = dummy;

        int carry = 0;

        while (a != null || b != null || carry != 0) {
            int sum = carry;

            if (a != null) {
                sum += a.data;
                a = a.next;
            }

            if (b != null) {
                sum += b.data;
                b = b.next;
            }

            carry = sum / 10;

            current.next = new Week11P3Node(sum % 10);
            current = current.next;
        }

        return dummy.next;
    }

    static void printList(Week11P3Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Week11P3Node a = new Week11P3Node(2);
        a.next = new Week11P3Node(4);
        a.next.next = new Week11P3Node(3);

        Week11P3Node b = new Week11P3Node(5);
        b.next = new Week11P3Node(6);
        b.next.next = new Week11P3Node(4);

        Week11P3Node result = addNumbers(a, b);
        printList(result);

        Week11P3Node x = new Week11P3Node(9);
        x.next = new Week11P3Node(9);

        Week11P3Node y = new Week11P3Node(1);

        printList(addNumbers(x, y));
    }
}