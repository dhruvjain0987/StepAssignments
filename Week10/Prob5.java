package Week10;

class PolyNode {
    int coeff, exp;
    PolyNode next;

    PolyNode(int coeff, int exp) {
        this.coeff = coeff;
        this.exp = exp;
        this.next = null;
    }
}

public class Prob5 {

    public static PolyNode addPolynomials(PolyNode p, PolyNode q) {
        PolyNode dummy = new PolyNode(0, 0);
        PolyNode tail = dummy;

        while (p != null && q != null) {
            if (p.exp > q.exp) {
                tail.next = new PolyNode(p.coeff, p.exp);
                p = p.next;
            }
            else if (q.exp > p.exp) {
                tail.next = new PolyNode(q.coeff, q.exp);
                q = q.next;
            }
            else {
                int sum = p.coeff + q.coeff;

                if (sum != 0) {
                    tail.next = new PolyNode(sum, p.exp);
                }

                p = p.next;
                q = q.next;

                if (sum != 0) {
                    tail = tail.next;
                }
                continue;
            }

            tail = tail.next;
        }

        while (p != null) {
            tail.next = new PolyNode(p.coeff, p.exp);
            tail = tail.next;
            p = p.next;
        }

        while (q != null) {
            tail.next = new PolyNode(q.coeff, q.exp);
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    public static void printPolynomial(PolyNode head) {
        if (head == null) {
            System.out.println("0");
            return;
        }

        while (head != null) {
            System.out.print(head.coeff);

            if (head.exp > 0) {
                System.out.print("x");
                if (head.exp > 1) {
                    System.out.print("^" + head.exp);
                }
            }

            head = head.next;

            if (head != null) {
                System.out.print(" + ");
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        PolyNode p = new PolyNode(5, 3);
        p.next = new PolyNode(4, 2);
        p.next.next = new PolyNode(2, 0);

        PolyNode q = new PolyNode(5, 2);
        q.next = new PolyNode(-3, 1);
        q.next.next = new PolyNode(1, 0);

        PolyNode result = addPolynomials(p, q);

        printPolynomial(result);
    }
}