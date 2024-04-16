package labs.lab_two.design_patterns.singleton;

public class Logger {
    private static volatile Logger instance;

    private Logger() {
    }

    // Static method to get the singleton instance
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    // Method for logging
    public void log(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Both logger1 and logger2 point to the same instance
        System.out.println(logger1 == logger2); // Output: true

        logger2.log("Hello world!");
    }
}

