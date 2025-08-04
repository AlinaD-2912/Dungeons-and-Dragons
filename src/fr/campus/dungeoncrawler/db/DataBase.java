package fr.campus.dungeoncrawler.db;

import java.sql.*;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;



import fr.campus.dungeoncrawler.normal_tiles.EmptyTile;
import fr.campus.dungeoncrawler.normal_tiles.EnemyTile;
import fr.campus.dungeoncrawler.surprise_tiles.*;
import fr.campus.dungeoncrawler.game_engine.Tile;

public class DataBase {

    //links for the correct connection
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dungeons_and_dragons";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";


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

        int generatedId = -1;

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            Gson gson = new Gson();
            String offensiveJson = gson.toJson(offensiveEquipment);
            String defensiveJson = gson.toJson(defensiveEquipment);

            stmt.setString(1, type);
            stmt.setString(2, name);
            stmt.setInt(3, lifePoints);
            stmt.setInt(4, strength);
            stmt.setString(5, offensiveJson);  // Stored as JSON
            stmt.setString(6, defensiveJson);  // Stored as JSON

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
                System.out.println("Character saved with ID: " + generatedId);
            }

        } catch (SQLException e) {
            System.err.println("Failed to save character: " + e.getMessage());
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

    public void insertBoard(String playerName, Tile[] board) {
        String insertBoard = "INSERT INTO boards (player_name, board_json) VALUES (?, ?)";

        try {
            // Register polymorphic types for proper deserialization later
            ObjectMapper mapper = new ObjectMapper();
            mapper.activateDefaultTyping(
                    LaissezFaireSubTypeValidator.instance,
                    ObjectMapper.DefaultTyping.NON_FINAL
            );

            // Optional: Register known subtypes if needed (explicit typing)
            mapper.registerSubtypes(
                    new NamedType(EmptyTile.class, "empty"),
                    new NamedType(EnemyTile.class, "enemy"),
                    new NamedType(Potion.class, "potion"),
                    new NamedType(Spell.class, "spell"),
                    new NamedType(Weapon.class, "weapon")
            );

            String jsonBoard = mapper.writeValueAsString(board); // serialize to JSON

            try (PreparedStatement stmt = connection.prepareStatement(insertBoard)) {
                stmt.setString(1, playerName);
                stmt.setString(2, jsonBoard);
                stmt.executeUpdate();
                System.out.println(" Board saved to DB!");
            }

        } catch (Exception e) {
            System.err.println(" Failed to save board: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Tile[] getBoardByPlayer(String playerName) {
        String query = "SELECT board_json FROM boards WHERE player_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, playerName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String json = rs.getString("board_json");

                ObjectMapper mapper = new ObjectMapper();
                mapper.activateDefaultTyping(
                        LaissezFaireSubTypeValidator.instance,
                        ObjectMapper.DefaultTyping.NON_FINAL
                );

                return mapper.readValue(json, Tile[].class);
            }
        } catch (Exception e) {
            System.err.println(" Failed to read board: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }






}
