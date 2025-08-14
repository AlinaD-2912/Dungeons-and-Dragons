package fr.campus.dungeoncrawler;
import fr.campus.dungeoncrawler.gui.MainWindow;

import javax.swing.*;

public class Main {
    /**
     * Main send a signal to menu to send the main menu with choices to the player
     */
    public static void main(String[] args) {


        SwingUtilities.invokeLater(MainWindow::new);

//        Menu menu = new Menu();
//        menu.start();
    }


}