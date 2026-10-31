package Week10;

class PalindromeNode {
    int data;
    PalindromeNode next;

    PalindromeNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Prob4 {

    public static PalindromeNode reverse(PalindromeNode head) {
        PalindromeNode prev = null;
        PalindromeNode curr = head;

        while (curr != null) {
            PalindromeNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static boolean isPalindrome(PalindromeNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        PalindromeNode slow = head;
        PalindromeNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        PalindromeNode secondHalf = reverse(slow.next);
        PalindromeNode firstHalf = head;

        boolean result = true;
        PalindromeNode temp = secondHalf;

        while (temp != null) {
            if (firstHalf.data != temp.data) {
                result = false;
                break;
            }

            firstHalf = firstHalf.next;
            temp = temp.next;
        }

        slow.next = reverse(secondHalf);

        return result;
    }

    public static void printList(PalindromeNode head) {
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
        PalindromeNode head = new PalindromeNode(1);
        head.next = new PalindromeNode(2);
        head.next.next = new PalindromeNode(3);
        head.next.next.next = new PalindromeNode(2);
        head.next.next.next.next = new PalindromeNode(1);

        System.out.println(isPalindrome(head));
        printList(head);

        PalindromeNode head2 = new PalindromeNode(1);
        head2.next = new PalindromeNode(2);
        head2.next.next = new PalindromeNode(3);
        head2.next.next.next = new PalindromeNode(2);

        System.out.println(isPalindrome(head2));
        printList(head2);
    }
}