package labs.lab_two.design_patterns.decorator;


interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle");
    }
}

abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }
}

class ColoredBorderDecorator extends ShapeDecorator {
    private String color;

    public ColoredBorderDecorator(Shape decoratedShape, String color) {
        super(decoratedShape);
        this.color = color;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setBorderColor(decoratedShape, color);
    }

    private void setBorderColor(Shape decoratedShape, String color) {
        System.out.println("Adding " + color + " border");
    }
}

class TransparencyDecorator extends ShapeDecorator {
    private double transparency;

    public TransparencyDecorator(Shape decoratedShape, double transparency) {
        super(decoratedShape);
        this.transparency = transparency;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setTransparency(decoratedShape, transparency);
    }

    private void setTransparency(Shape decoratedShape, double transparency) {
        System.out.println("Setting transparency to " + transparency * 100 + "%");
    }
}


public class ShapeDecoratorClient {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape rectangle = new Rectangle();

        Shape redBorderCircle = new ColoredBorderDecorator(circle, "red");
        Shape transparentRectangle = new TransparencyDecorator(rectangle, 0.5);

        System.out.println("Circle with red border:");
        redBorderCircle.draw();

        System.out.println("\nRectangle with 50% transparency:");
        transparentRectangle.draw();

        // Combine multiple decorators
        Shape redBorderTransparentCircle = new TransparencyDecorator(new ColoredBorderDecorator(circle, "red"), 0.75);
        System.out.println("\nCircle with red border and 75% transparency:");
        redBorderTransparentCircle.draw();
    }
}
