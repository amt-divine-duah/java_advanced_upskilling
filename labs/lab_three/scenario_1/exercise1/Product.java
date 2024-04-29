package labs.lab_three.scenario_1.exercise1;

public record Product(String name, double price, String category) {

    // accessor methods
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
