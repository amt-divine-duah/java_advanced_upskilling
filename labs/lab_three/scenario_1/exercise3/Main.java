package labs.lab_three.scenario_1.exercise3;

public class Main {

    public static void main(String[] args) {

        ElectronicsProduct electronicsProduct = new ElectronicsProduct("Laptop", 1000.0, "Electronics");
        ClothingProduct clothingProduct = new ClothingProduct("T-Shirt", 20.0, "Clothing");

        System.out.println("This is an electronics product with the ff details: " + electronicsProduct.getCategory() + " " + electronicsProduct.getName() + " " + electronicsProduct.getPrice());

        System.out.println("This is a clothing product with the ff details: " + clothingProduct.getCategory() + " " + clothingProduct.getName() + " " + clothingProduct.getPrice());

        String categoryDescription = getCategoryDescription(electronicsProduct);

        System.out.println(categoryDescription);
    }

    public static String getCategoryDescription(Product product) {
        return switch (product.getCategory()) {
            case "Electronics" -> """
                    This is an electronics product
                    """;
            case "Clothing" -> """
                    This is a clothing product
                    """;
            default -> "No category found";
        };

    }
}
