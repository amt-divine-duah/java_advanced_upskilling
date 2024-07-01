package labs.lab_two.design_patterns.adapter;

interface ModernPaymentGateway {
    void processPayment(String cardNumber, String date, double amount, String merchant);
}

class PaymentAdapter implements ModernPaymentGateway {
    private final LegacyPaymentProcessor legacyProcessor;

    public PaymentAdapter(LegacyPaymentProcessor legacyProcessor) {
        this.legacyProcessor = legacyProcessor;
    }

    @Override
    public void processPayment(String cardNumber, String date, double amount, String merchant) {
        legacyProcessor.executeTransaction(cardNumber, date, amount, merchant);
    }
}


public class LegacyPaymentProcessor {
    public void executeTransaction(String accountNumber, String expiry, double total, String vendor) {
        System.out.println("Legacy transaction complete:" +
                "\nAccount Number: " + accountNumber +
                "\nExpiry: " + expiry +
                "\nTotal: " + total +
                "\nVendor: " + vendor);
    }

    public static void main(String[] args) {
        LegacyPaymentProcessor legacyProcessor = new LegacyPaymentProcessor();
        ModernPaymentGateway modernGateway = new PaymentAdapter(legacyProcessor);

        modernGateway.processPayment("1234-5678-9012-3456", "12/24", 100.00, "AmaliTech Merchant");
    }
}
