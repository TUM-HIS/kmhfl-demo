package tum.devs.kmhfldemo.utils.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVImporter {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/mydatabase";
        String username = "your-username";
        String password = "your-password";
        String csvFile = "data.csv";
        String tableName = "mytable";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            String headerRow = reader.readLine();
            String[] columns = headerRow.split(",");

            // Create the table using header row as column names
            String createTableQuery = "CREATE TABLE " + tableName + " (" + String.join(",", columns) + ")";
            try (PreparedStatement createTableStmt = conn.prepareStatement(createTableQuery)) {
                createTableStmt.executeUpdate();
            }

            // Insert data into the table
            String insertQuery = "INSERT INTO " + tableName + " VALUES (" + generatePlaceholderString(columns.length) + ")";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    for (int i = 0; i < values.length; i++) {
                        insertStmt.setString(i + 1, values[i]);
                    }
                    insertStmt.executeUpdate();
                }
            }

            System.out.println("Data imported successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String generatePlaceholderString(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("?");
            if (i != count - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
