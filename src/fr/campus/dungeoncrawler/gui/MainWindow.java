package fr.campus.dungeoncrawler.gui;

import fr.campus.dungeoncrawler.characters.Character;   // ensure this import
import fr.campus.dungeoncrawler.enemies.Enemy;

import javax.swing.*;

// Main controller JFrame

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Dungeons and Dragons");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showMainMenu();
        setVisible(true);
    }

    public void showMainMenu() {
        setContentPane(new MainMenuPanel(this));
        revalidate();
    }

    public void openSubMenu(Character player) {
        setContentPane(new SubMenuPanel(this, player));
        revalidate();
    }

    public void showFightPanel(Character player, Enemy enemy) {
        setContentPane(new FightPanel(this, player, enemy));
        revalidate();
    }
}
