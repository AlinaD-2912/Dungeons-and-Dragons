package fr.campus.dungeoncrawler.game_engine;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.Warrior;
import fr.campus.dungeoncrawler.characters.Wizard;
import fr.campus.dungeoncrawler.game_engine.ScannerHelper;


public class Menu {

    // while choosing character in scanner give a choice to show info, modify info, quit game
    public void start() {
        Character player = null;

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Create character");
            System.out.println("2. Quit");

            int choice = ScannerHelper.getInt("Choose: ");
            if (choice == 2) break;

            String type = ScannerHelper.getString("Type (Wizard/Warrior): ");
            String name = ScannerHelper.getString("Name: ");

            if (type.equalsIgnoreCase("Wizard")) {
                player = new Wizard(name);
            } else if (type.equalsIgnoreCase("Warrior")) {
                player = new Warrior(name);
            } else {
                System.out.println("Unknown type. Try again.");
                continue; // restart main loop
            }

            boolean inSubmenu = true;
            while (inSubmenu) {
                System.out.println("\n--- Character Menu ---");
                System.out.println("1. Show character");
                System.out.println("2. Modify name");
                System.out.println("3. Start game");
                System.out.println("4. Back");

                int sub = ScannerHelper.getInt("Your choice: ");
                switch (sub) {
                    case 1 -> {
                        System.out.println(player);
                        if (player instanceof Wizard) {
                            System.out.println("""
                                     /|\\
                                    (∩｀-´)⊃━☆ﾟ.*･｡ﾟ
                                     (  )
                                      / \\
                                    WIZARD
                                    """);
                        } else if (player instanceof Warrior) {
                            System.out.println("""
                                    ╭────╮
                                    (งಠ_ಠ)ง──━一
                                     /█\\\s
                                    _| |_
                                    WARRIOR
                                    """);
                        }

                    }
                    case 2 -> {
                        String newName = ScannerHelper.getString("New name: ");
                        player.setName(newName);
                    }
                    case 3 -> {
                        Game game = new Game(player);
                        game.start();
                    }
                    case 4 -> inSubmenu = false;
                    default -> System.out.println("Invalid option.");
                }
            }
        }


    }

}
