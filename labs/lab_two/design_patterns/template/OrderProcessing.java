package labs.lab_two.design_patterns.template;

abstract class OrderProcessingTemplate {

    // Template method
    public final void processOrder(String item, int quantity) {
        validateOrder(item, quantity);
        if (checkInventory(item, quantity)) {
            processPayment(item, quantity);
            arrangeDelivery(item, quantity);
            System.out.println("Order processed successfully for " + item + ", quantity: " + quantity);
        } else {
            System.out.println("Insufficient inventory for " + item);
        }
    }

    // Abstract methods to be implemented by subclasses
    protected abstract void validateOrder(String item, int quantity);

    protected abstract boolean checkInventory(String item, int quantity);

    protected abstract void processPayment(String item, int quantity);

    protected abstract void arrangeDelivery(String item, int quantity);
}

class DomesticOrderProcessing extends OrderProcessingTemplate {

    @Override
    protected void validateOrder(String item, int quantity) {
        // Perform additional validations for domestic orders (if any)
        System.out.println("Validating domestic order for " + item);
    }

    @Override
    protected boolean checkInventory(String item, int quantity) {
        // Implement domestic inventory check logic
        return true;
    }

    @Override
    protected void processPayment(String item, int quantity) {
        // Handle domestic payment processing
        System.out.println("Processing payment for domestic order of " + item);
    }

    @Override
    protected void arrangeDelivery(String item, int quantity) {
        // Arrange domestic delivery
        System.out.println("Arranging delivery for domestic order of " + item);
    }
}

class InternationalOrderProcessing extends OrderProcessingTemplate {

    @Override
    protected void validateOrder(String item, int quantity) {
        // Perform additional validations for international orders (e.g., customs regulations)
        System.out.println("Validating international order for " + item);
    }

    @Override
    protected boolean checkInventory(String item, int quantity) {
        // Implement international inventory check logic
        return true;
    }

    @Override
    protected void processPayment(String item, int quantity) {
        // Handle international payment processing
        System.out.println("Processing payment for international order of " + item);
    }

    @Override
    protected void arrangeDelivery(String item, int quantity) {
        // Arrange international delivery
        System.out.println("Arranging delivery for international order of " + item);
    }
}

public class OrderProcessing {

    public static void main(String[] args) {
        OrderProcessingTemplate domesticOrder = new DomesticOrderProcessing();
        OrderProcessingTemplate internationalOrder = new InternationalOrderProcessing();

        System.out.println("Processing Domestic Order:");
        domesticOrder.processOrder("Chair", 2);

        System.out.println("\nProcessing International Order:");
        internationalOrder.processOrder("Table", 1);
    }
}
