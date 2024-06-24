package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection;

    public static Connection openConnection() {
        String connectionString = "jdbc:mysql://localhost:3306/quanlibansach";
        String username = "root";
        String password = "";
        try {
            connection = DriverManager.getConnection(connectionString, username, password);
           // System.out.println("Connected to database.");
            
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
        return connection ; 
        
    }

  
}
