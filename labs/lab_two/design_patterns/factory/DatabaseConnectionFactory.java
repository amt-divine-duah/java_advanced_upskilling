package labs.lab_two.design_patterns.factory;


interface DatabaseConnection {
    void connect();
    void disconnect();
}


class MySQLConnection implements DatabaseConnection {
    @Override
    public void connect() {
        System.out.println("Connecting to MySQL database...");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from MySQL database...");
    }
}

class PostgreSQLConnection implements DatabaseConnection {
    @Override
    public void connect() {
        System.out.println("Connecting to PostgreSQL database...");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from PostgreSQL database...");
    }
}


public class DatabaseConnectionFactory {
    DatabaseConnection getConnection(String dbType) {
        if (dbType == null) {
            return null;
        }
        if (dbType.equalsIgnoreCase("MYSQL")) {
            return new MySQLConnection();
        } else if (dbType.equalsIgnoreCase("POSTGRESQL")) {
            return new PostgreSQLConnection();
        }
        return null; // Unsupported database
    }

    public static void main(String[] args) {
        DatabaseConnectionFactory factory = new DatabaseConnectionFactory();

        DatabaseConnection mySql = factory.getConnection("MYSQL");
        mySql.connect();
        mySql.disconnect();

        DatabaseConnection postgreSql = factory.getConnection("POSTGRESQL");
        postgreSql.connect();
        postgreSql.disconnect();

        // Attempt to request an unsupported database connection
        DatabaseConnection unsupported = factory.getConnection("ORACLE");
        if (unsupported != null) {
            unsupported.connect();
            unsupported.disconnect();
        } else {
            System.out.println("Unsupported database type");
        }
    }
}

