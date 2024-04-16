package labs.lab_two.design_patterns.singleton;

import com.sun.tools.javac.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigurationManager {
    private static volatile ConfigurationManager instance;
    private Properties properties;

    private ConfigurationManager() {
        // Load configuration data from file or environment variables
        this.properties = new Properties();
        try {
            // Load configuration from a file
            try (FileInputStream input = new FileInputStream("C:\\Santana\\Java Tutorials\\Amalitech Upskilling\\java_advanced_upskilling\\labs\\lab_two\\design_patterns\\singleton\\config.properties")) {
                properties.load(input);
            }
        } catch (IOException e) {
            // Handle file not found or other IO exceptions
            e.printStackTrace();
        }
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Example usage
    public static void main(String[] args) {
        ConfigurationManager configManager = ConfigurationManager.getInstance();

        // Access configuration values
        String dbHost = configManager.getProperty("db.host");
        String dbPort = configManager.getProperty("db.port");

        System.out.println("Database Host: " + dbHost);
        System.out.println("Database Port: " + dbPort);
    }
}

