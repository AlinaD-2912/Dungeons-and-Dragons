package fr.campus.dungeoncrawler.gui;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.Warrior;
import fr.campus.dungeoncrawler.characters.Wizard;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SubMenuPanel extends JPanel {
    private JPanel centerPanel; // Panel to hold dynamic content (character info)
    private Character player;

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
        showCharacter.addActionListener(e -> showCharacterInfo());

        modifyCharacter.addActionListener(e -> modifyCharacterName());

        startGame.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Starting the game with " + player.getName() + "!");
            // Here you would call window.startActualGame(player) or similar
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
    }

    private void showCharacterInfo() {
        // Clear previous content
        centerPanel.removeAll();

        // Create character info display
        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setFont(new Font("Monospaced", Font.PLAIN, 14));
        info.setBorder(BorderFactory.createTitledBorder("Character Information"));
        info.setText("Name: " + player.getName() + "\n" +
                "Type: " + player.getType() + "\n" +
                "Attack: " + player.getAttackLevel() + "\n" +
                "Life: " + player.getLifeLevel());

        // Add scroll pane in case of long text
        JScrollPane scrollPane = new JScrollPane(info);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Refresh the panel to show changes
        centerPanel.revalidate();
        centerPanel.repaint();

        System.out.println("Character info displayed!"); // Debug output
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