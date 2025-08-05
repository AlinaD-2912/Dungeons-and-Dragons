package fr.campus.dungeoncrawler.gui;


import fr.campus.dungeoncrawler.characters.Character;

import javax.swing.*;
import java.awt.*;

// Displays player stats and "start" game
public class GamePanel extends JPanel {

    public GamePanel(MainWindow window, Character player) {
        setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Welcome, " + player.getName() + "!", JLabel.CENTER);
        welcome.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(welcome, BorderLayout.NORTH);

        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setText("Type: " + player.getType() + "\n"
                + "Attack: " + player.getAttackLevel() + "\n"
                + "Life: " + player.getLifeLevel());
        add(info, BorderLayout.CENTER);

        JButton startBtn = new JButton("Start Game (placeholder)");
        startBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "This is where the board will go.");
        });
        add(startBtn, BorderLayout.SOUTH);
    }
}
