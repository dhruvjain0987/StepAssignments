package Week10;

class BrowserNode {
    String url;
    BrowserNode prev, next;

    BrowserNode(String url) {
        this.url = url;
    }
}

public class Prob2 {
    BrowserNode current;

    Prob2(String home) {
        current = new BrowserNode(home);
    }

    public String visit(String url) {
        BrowserNode newNode = new BrowserNode(url);
        current.next = newNode;
        newNode.prev = current;
        current = newNode;
        return current.url;
    }

    public String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current.url;
    }

    public String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current.url;
    }

    public static void main(String[] args) {
        Prob2 browser = new Prob2("srm.edu");

        browser.visit("a.com");
        browser.visit("b.com");
        browser.visit("c.com");

        System.out.println(browser.back(1));
        System.out.println(browser.back(1));
        System.out.println(browser.forward(1));

        browser.visit("d.com");

        System.out.println(browser.forward(2));
        System.out.println(browser.back(2));
        System.out.println(browser.back(7));
    }
}