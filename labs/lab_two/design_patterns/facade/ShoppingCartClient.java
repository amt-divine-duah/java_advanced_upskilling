package labs.lab_two.design_patterns.facade;

import java.util.ArrayList;
import java.util.List;

class ItemService {
    private List<String> items = new ArrayList<>();

    public void addItem(String item) {
        items.add(item);
        System.out.println("Item added: " + item);
    }

    public List<String> getItems() {
        return items;
    }
}

class DiscountService {
    public double calculateDiscount(List<String> items) {
        // For simplicity, let's say each item gets a fixed discount of $5
        double discount = items.size() * 5.0;
        System.out.println("Total discount: $" + discount);
        return discount;
    }
}

class CheckoutService {
    public void checkout(List<String> items, double totalDiscount) {
        double totalPrice = items.size() * 20.0; // Assuming each item costs $20
        double finalPrice = totalPrice - totalDiscount;
        System.out.println("Total price after discount: $" + finalPrice);
        System.out.println("Checkout initiated...");
    }
}

class ShoppingCartFacade {
    private final ItemService itemService;
    private final DiscountService discountService;
    private final CheckoutService checkoutService;

    public ShoppingCartFacade() {
        this.itemService = new ItemService();
        this.discountService = new DiscountService();
        this.checkoutService = new CheckoutService();
    }

    public void addItem(String item) {
        itemService.addItem(item);
    }

    public void checkout() {
        List<String> items = itemService.getItems();
        double discount = discountService.calculateDiscount(items);
        checkoutService.checkout(items, discount);
    }
}


public class ShoppingCartClient {
    public static void main(String[] args) {
        ShoppingCartFacade shoppingCart = new ShoppingCartFacade();

        // Adding items to the cart
        shoppingCart.addItem("Laptop");
        shoppingCart.addItem("Smartphone");

        // Initiating checkout
        shoppingCart.checkout();
    }
}
