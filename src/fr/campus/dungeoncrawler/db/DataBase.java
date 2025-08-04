package fr.campus.dungeoncrawler.db;

import java.sql.*;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.campus.dungeoncrawler.surprise_tiles.DefensiveEquipment;
import fr.campus.dungeoncrawler.surprise_tiles.OffensiveEquipment;

public class DataBase {

    //links for the correct connection
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dungeons_and_dragons";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";

//    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    String json = gson.toJson(this);

    //package iported by java.sql
    private Connection connection;

    public void connect() {
        try {
            //getConnection is methode imported by java.sql and attempts to establish a connection to the given database URL
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println(" Connected to MySQL database!");
        } catch (SQLException e) {
            System.err.println(" Connection failed: " + e.getMessage());
        }
    }

    public int insertCharacter(String type, String name, int lifePoints, int strength,
                               OffensiveEquipment offensiveEquipment, DefensiveEquipment defensiveEquipment)
    {
        String query = "INSERT INTO characters (type, name, lifePoints, strength, offensiveEquipment, defensiveEquipment) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

//        final GsonBuilder builder = new GsonBuilder();
//        final Gson gson = builder.create();
//        final Map<String, String, Integer, Integer> valeurs = new HashMap<Integer, String>();

        int generatedId = -1;

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, type);
            stmt.setString(2, name);
            stmt.setInt(3, lifePoints);
            stmt.setInt(4, strength);
            stmt.setInt(5, offensiveEquipment.getId());
            stmt.setInt(6, defensiveEquipment.getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
                System.out.println("Character saved with ID: " + generatedId);
            }

        } catch (SQLException e) {
            System.err.println(" Failed to save character: " + e.getMessage());
        }

        return generatedId;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println(" Disconnected from database.");
            }
        } catch (SQLException e) {
            System.err.println(" Failed to close connection: " + e.getMessage());
        }
    }

    public void updateCharacterNameById(int id, String newName) {
        String query = "UPDATE characters SET name = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Character name updated by ID!");
            } else {
                System.out.println("No character found with that ID.");
            }

        } catch (SQLException e) {
            System.err.println("Failed to update character by ID: " + e.getMessage());
        }
    }

    // In DataBase.java
    public void deleteCharacterById(int id) {
        String sql = "DELETE FROM characters WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Character deleted from database.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to delete character: " + e.getMessage());
        }
    }



}
