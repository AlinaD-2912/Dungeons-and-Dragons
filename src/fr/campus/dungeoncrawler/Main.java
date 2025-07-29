package fr.campus.dungeoncrawler;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.game_engine.Menu;

public class Main {
//    public static void main(String[] args) {
//        //game flow
//        Character warrior = new Character("Warrior", "Thorin", 10, 5, "Axe");
//        System.out.println(warrior);
//    }
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();
    }


}