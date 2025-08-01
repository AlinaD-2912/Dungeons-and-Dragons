package fr.campus.dungeoncrawler.gui;

import fr.campus.dungeoncrawler.characters.Character;   // ensure this import
import fr.campus.dungeoncrawler.enemies.Enemy;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("Dungeon Crawler");
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

    public void startGame(Character player) {
        setContentPane(new GamePanel(this, player));
        revalidate();
    }

    public void showFightPanel(Character player, Enemy enemy) {
        setContentPane(new FightPanel(this, player, enemy));
        revalidate();
    }
}
