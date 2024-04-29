package labs.lab_three.scenario_1.exercise2;

public class Main {

    public static void main(String[] args) {

        ElectronicsProduct electronicsProduct = new ElectronicsProduct("Laptop", 1000.0, "Electronics");
        ClothingProduct clothingProduct = new ClothingProduct("T-Shirt", 20.0, "Clothing");

        System.out.println("This is an electronics product with the ff details: " + electronicsProduct.getCategory() + " " + electronicsProduct.getName() + " " + electronicsProduct.getPrice());

        System.out.println("This is a clothing product with the ff details: " + clothingProduct.getCategory() + " " + clothingProduct.getName() + " " + clothingProduct.getPrice());
    }
}
