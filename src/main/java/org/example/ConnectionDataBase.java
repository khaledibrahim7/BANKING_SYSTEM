package org.example;

import java.sql.*;

public class ConnectionDataBase {

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=BankSystem;integratedSecurity=true;encrypt=false;trustServerCertificate=true;");
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to DB", e);
        }
    }

    public static void runSelect(String query, Object... params) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                while (rs.next()) {
                    StringBuilder row = new StringBuilder("Row: ");
                    for (int i = 1; i <= columnCount; i++) {
                        row.append(rs.getString(i)).append(" | ");
                    }
                    System.out.println(row);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error running SELECT", e);
        }
    }

    public static void executeQ(String query, Object... params) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Query executed successfully, rows affected: " + rowsAffected);

        } catch (SQLException e) {
            throw new RuntimeException("Error executing query", e);
        }
    }
}
