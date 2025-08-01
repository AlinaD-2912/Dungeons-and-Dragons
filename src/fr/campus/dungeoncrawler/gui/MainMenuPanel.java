package fr.campus.dungeoncrawler.gui;

import fr.campus.dungeoncrawler.characters.Character; // added
import fr.campus.dungeoncrawler.characters.Warrior;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel(GameWindow window) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Dungeon Crawler", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 32));
        add(title, BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        JButton createChar = new JButton("Create Character");
        JButton quit       = new JButton("Quit");

        createChar.addActionListener(e -> {
            Character player = new Warrior("Hero");
            window.startGame(player);
        });

        quit.addActionListener(e -> System.exit(0));

        buttons.add(createChar);
        buttons.add(quit);
        add(buttons, BorderLayout.CENTER);
    }
}
