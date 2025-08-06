package fr.campus.dungeoncrawler.gui;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.Warrior;
import fr.campus.dungeoncrawler.game_engine.Board;
import fr.campus.dungeoncrawler.characters.Wizard;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SubMenuPanel extends JPanel {
    private JPanel centerPanel; // Panel to hold dynamic content (character info)
    private Character player;
    private Board board;

    public SubMenuPanel(MainWindow window, Character player) {
        this.player = player;
        setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Welcome, " + player.getName() + "!", JLabel.CENTER);
        welcome.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(welcome, BorderLayout.NORTH);

        // Create center panel for dynamic content
        centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttons = new JPanel();

        JButton showCharacter = new JButton("Show character");
        JButton modifyCharacter = new JButton("Modify name");
        JButton startGame = new JButton("Start game");
        JButton goBack = new JButton("Back");

        // Button styling
        showCharacter.setBackground(Color.YELLOW);
        showCharacter.setForeground(Color.BLACK);
        showCharacter.setOpaque(true);
        showCharacter.setBorderPainted(false);

        modifyCharacter.setBackground(Color.RED);
        modifyCharacter.setForeground(Color.WHITE);
        modifyCharacter.setOpaque(true);
        modifyCharacter.setBorderPainted(false);

        startGame.setBackground(Color.GREEN);
        startGame.setForeground(Color.BLACK);
        startGame.setOpaque(true);
        startGame.setBorderPainted(false);

        goBack.setBackground(Color.GRAY);
        goBack.setForeground(Color.WHITE);
        goBack.setOpaque(true);
        goBack.setBorderPainted(false);

        // Add action listeners
        showCharacter.addActionListener(e -> {
            // Creates text area
            JTextArea info = new JTextArea();
            info.setEditable(false);
            info.setFont(new Font("Monospaced", Font.PLAIN, 14));
            info.setBorder(BorderFactory.createTitledBorder("Character Information"));
            info.setText("Name: " + player.getName() + "\n" +
                    "Type: " + player.getType() + "\n" +
                    "Attack: " + player.getAttackLevel() + "\n" +
                    "Life: " + player.getLifeLevel());

            JScrollPane scrollPane = new JScrollPane(info);
            centerPanel.add(scrollPane, BorderLayout.CENTER); // Text in CENTER

            centerPanel.revalidate();
            centerPanel.repaint();
        });

        modifyCharacter.addActionListener(e -> modifyCharacterName());

        startGame.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Starting the game with " + player.getName() + "!");
            Board board = new Board(player);
            window.startGame(player, board);
        });

        goBack.addActionListener(e -> {
            window.showMainMenu();
            JOptionPane.showMessageDialog(this, "Going back to main menu...");
        });

        buttons.add(showCharacter);
        buttons.add(modifyCharacter);
        buttons.add(startGame);
        buttons.add(goBack);

        add(buttons, BorderLayout.SOUTH);

        showCharacterInfo();
    }

    private void showCharacterInfo() {
        centerPanel.removeAll();

        URL imageURL = getClass().getResource("/fr/campus/dungeoncrawler/images/wizard.jpg");
        // Add image to WEST or NORTH
        if (player instanceof Warrior) {
            imageURL = getClass().getResource("/fr/campus/dungeoncrawler/images/warrior.jpg");
        }

        if (imageURL != null) {
            ImageIcon originalIcon = new ImageIcon(imageURL);
            Image originalImg = originalIcon.getImage();

            // Scale to 150x150 pixels
            Image scaledImg = originalImg.getScaledInstance(600, 300, Image.SCALE_SMOOTH);

            JLabel bgLabel = new JLabel(new ImageIcon(scaledImg));
            bgLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Optional border
            centerPanel.add(bgLabel, BorderLayout.NORTH);
        }


    }

    private void modifyCharacterName() {
        String newName = JOptionPane.showInputDialog(
                this,
                "Enter new name for your character:",
                "Modify Character Name",
                JOptionPane.QUESTION_MESSAGE
        );

        if (newName != null && !newName.trim().isEmpty()) {
            player.setName(newName.trim()); // Assuming you have a setName method

            // Update the welcome label
            Component[] components = getComponents();
            for (Component comp : components) {
                if (comp instanceof JLabel) {
                    JLabel label = (JLabel) comp;
                    if (label.getText().startsWith("Welcome,")) {
                        label.setText("Welcome, " + player.getName() + "!");
                        break;
                    }
                }
            }

            // If character info is currently displayed, refresh it
            if (centerPanel.getComponentCount() > 0) {
                showCharacterInfo();
            }

            JOptionPane.showMessageDialog(this, "Character name updated to: " + newName);
        }
    }
}