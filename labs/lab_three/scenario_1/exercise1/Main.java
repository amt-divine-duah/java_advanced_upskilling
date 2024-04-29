package labs.lab_three.scenario_1.exercise1;

public class Main {
    public static void main(String[] args) {
        Product product = new Product("Product 1", 10.0, "Category 1");
        System.out.println("The name of the product is " + product.getName() + " and its category is " + product.getCategory());
    }
}
