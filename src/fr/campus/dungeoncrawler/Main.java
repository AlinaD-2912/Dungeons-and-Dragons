package fr.campus.dungeoncrawler;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.game_engine.Menu;
import fr.campus.dungeoncrawler.gui.GameWindow;

import javax.swing.*;

public class Main {
    /**
     * Main send a signal to menu to send the main menu with choices to the player
     *
     */
    public static void main(String[] args) {
//        Menu menu = new Menu();
//        menu.start();

        SwingUtilities.invokeLater(GameWindow::new);
    }


}