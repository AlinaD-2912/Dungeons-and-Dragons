package fr.campus.dungeoncrawler.gui;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.Warrior;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel(GameWindow window) {
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


        createChar.addActionListener(e -> {
            Character player = new Warrior("Hero");
            window.startGame(player);
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
}