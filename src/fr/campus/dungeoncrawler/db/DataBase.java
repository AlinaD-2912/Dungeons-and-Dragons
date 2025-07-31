package fr.campus.dungeoncrawler.db;

import java.sql.*;

public class DataBase {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dungeon_game";
    private static final String DB_USER = "your_mysql_username";
    private static final String DB_PASSWORD = "your_mysql_password";

    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println(" Connected to MySQL database!");
        } catch (SQLException e) {
            System.err.println(" Connection failed: " + e.getMessage());
        }
    }

    public void insertCharacter(String type, String name, int lifePoints, int strength,
                                String offensiveEquipment, String defensiveEquipment) {
        String query = "INSERT INTO character (type, name, lifePoints, strength, offensiveEquipment, defensiveEquipment) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, type);
            stmt.setString(2, name);
            stmt.setInt(3, lifePoints);
            stmt.setInt(4, strength);
            stmt.setString(5, offensiveEquipment);
            stmt.setString(6, defensiveEquipment);
            stmt.executeUpdate();

            System.out.println(" Character saved to database!");
        } catch (SQLException e) {
            System.err.println(" Failed to save character: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("🔌 Disconnected from database.");
            }
        } catch (SQLException e) {
            System.err.println(" Failed to close connection: " + e.getMessage());
        }
    }
}
