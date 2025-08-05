package fr.campus.dungeoncrawler.gui;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.Warrior;
import fr.campus.dungeoncrawler.characters.Wizard;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel(MainWindow window) {
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Dungeons & Dragons", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 32));
        add(title, BorderLayout.NORTH);

        // Image with better error handling
        JLabel imageLabel = createImageLabel();
        add(imageLabel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttons = new JPanel();
        JButton createChar = new JButton("Create Character");

        JButton quit = new JButton("Quit");

        createChar.setBackground(Color.YELLOW);
        createChar.setForeground(Color.BLACK);
        createChar.setOpaque(true);
        createChar.setBorderPainted(false);

        quit.setBackground(Color.RED);
        quit.setForeground(Color.WHITE);
        quit.setOpaque(true);
        quit.setBorderPainted(false);

        //hover effect for buttons
        createChar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createChar.setBackground(Color.YELLOW.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                createChar.setBackground(Color.YELLOW);
            }
        });

        quit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                quit.setBackground(Color.RED.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                quit.setBackground(Color.RED);
            }
        });


        createChar.addActionListener(e -> {
            window.openSubMenu(createcharacter());
        });

        quit.addActionListener(e -> System.exit(0));

        buttons.add(createChar);
        buttons.add(quit);
        add(buttons, BorderLayout.SOUTH); // Changed from CENTER to SOUTH

    }

    private JLabel createImageLabel() {
        String imagePath = "/fr/campus/dungeoncrawler/images/d&d.jpg";
        URL imageURL = getClass().getResource(imagePath);

        System.out.println("Image URL: " + imageURL);

        if (imageURL != null) {
            try {
                ImageIcon icon = new ImageIcon(imageURL);

                // Check if image loaded successfully
                if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
                    // Scale image to fit better (optional)
                    Image img = icon.getImage();
                    Image scaledImg = img.getScaledInstance(600, 500, Image.SCALE_SMOOTH);
                    return new JLabel(new ImageIcon(scaledImg));
                } else {
                    System.out.println("Image failed to load properly");
                    return new JLabel("Image could not be loaded", JLabel.CENTER);
                }
            } catch (Exception e) {
                System.out.println("Exception loading image: " + e.getMessage());
                return new JLabel("Error loading image", JLabel.CENTER);
            }
        } else {
            System.out.println("Image file not found at: " + imagePath);
            return new JLabel("Image not found", JLabel.CENTER);
        }
    }

    private Character createcharacter(){
        String[] options = {"Warrior", "Wizard"};
        String selectedType = (String) JOptionPane.showInputDialog(
                this,
                "Choose your character type:",
                "Character Type Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0] // Default selection
        );

        // If user cancelled the dialog
        if (selectedType == null) {
            return null;
        }

        // Step 2: Enter character name
        String characterName = JOptionPane.showInputDialog(
                this,
                "Enter your character's name:",
                "Character Name",
                JOptionPane.QUESTION_MESSAGE
        );

        // If user cancelled or entered empty name
        if (characterName == null || characterName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Character creation cancelled or invalid name entered.",
                    "Creation Cancelled",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return null;
        }

        // Create the character based on selection
        Character player;
        if (selectedType.equals("Warrior")) {
            player = new Warrior(characterName.trim());
        } else {
            player = new Wizard(characterName.trim());
        }
        return player;
    }




}
