package Week7;

abstract class Shape {
    private static int counter = 0;
    private final String shapeId;

    Shape() {
        counter++;
        shapeId = "SH-" + counter;
    }

    abstract double calculateArea();

    String getShapeId() {
        return shapeId;
    }

    void scale(double factor) {
    }

    void scale(double xFactor, double yFactor) {
        scale(xFactor);
        scale(yFactor);
    }

    static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }
}

class CircleShape extends Shape {
    double radius;

    CircleShape(double radius) {
        this.radius = radius;
    }

    double calculateArea() {
        return Math.PI * radius * radius;
    }

    void scale(double factor) {
        radius *= factor;
    }
}

class SquareShape extends Shape {
    double side;

    SquareShape(double side) {
        this.side = side;
    }

    double calculateArea() {
        return side * side;
    }

    void scale(double factor) {
        side *= factor;
    }
}

public class Prob1 {
    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        SquareShape sq = new SquareShape(4.0);

        System.out.println(c.calculateArea());
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        Shape.printArea(c);
        System.out.println(c.getShapeId());
    }
}