package fr.campus.dungeoncrawler.game_engine;

import fr.campus.dungeoncrawler.db.DataBase;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.Warrior;
import fr.campus.dungeoncrawler.characters.Wizard;

import java.awt.*;


public class Menu {
    /**
     * Menu is used for main menu choices and submenu choices
     * it cals ScannerHelper to read the player input and send the correct response to the program
     * it creates a player with new name and random attack and life levels and sends it further to class Game
     * it allows player to go back in his choice of character or quit the game
     * Menu sends -> player to -> Game
     * Menu creates -> player becomes -> reference to the new object Warrior or Wizard
    */

    private Graphic graphic;

    public void start() {
        Character player = null;


        while (true) {

            /*
            * Main menu
            * it allows to create character or quit the game
            * after character is created it disappears
             */

            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Create character");
            System.out.println("2. Quit");

            int choice = ScannerHelper.getInt("Choose: ");
            // if choice is 2 stop the program
            if (choice == 2) break;

            String type = ScannerHelper.getString("Type (Wizard/Warrior): ");
            String name = ScannerHelper.getString("Name: ");

            /*
            * Checks if the user input the same as one of 2 options Wizard or Warrior
            */
            if (type.equalsIgnoreCase("Wizard")) {
                player = new Wizard(name);
                DataBase dbManager = new DataBase();
                dbManager.connect();

                int id = dbManager.insertCharacter(
                        player.getType(),
                        player.getName(),
                        player.getLifeLevel(),
                        player.getAttackLevel(),
                        player.getOffensiveEquipment(),
                        "None"
                );

                player.setId(id);


                dbManager.close();

            } else if (type.equalsIgnoreCase("Warrior")) {
                player = new Warrior(name);
                DataBase dbManager = new DataBase();
                dbManager.connect();

                int id = dbManager.insertCharacter(
                        player.getType(),
                        player.getName(),
                        player.getLifeLevel(),
                        player.getAttackLevel(),
                        player.getOffensiveEquipment(),
                        "None"
                );
                player.setId(id);

            } else {
                System.out.println("Unknown type. Try again.");
                continue; // restart main loop
            }

            /*
             * Submenu
             * appears after character created
             * gives 4 options to player
             * remembers the created player and sends information about it to Game
             * starts the Game engine
             */
            boolean inSubmenu = true;
            while (inSubmenu) {
                System.out.println("\n--- Character Menu ---");
                System.out.println("1. Show character");
                System.out.println("2. Modify name");
                System.out.println("3. Start game");
                System.out.println("4. Back");

                int sub = ScannerHelper.getInt("Your choice: ");
                switch (sub) {
                    // allows to view full information about character
                    case 1 -> {
                        System.out.println("--- YOUR CHARACTER ---");
                        System.out.println("Name: " + player.getName());
                        System.out.println("Attack Level: " + player.getAttackLevel());
                        System.out.println("Life Level: " + player.getLifeLevel());
                        graphic = new Graphic();
                        System.out.println(graphic.characterGraphic(player));

                    }
                    // allows to modify character name
                    case 2 -> {
                        String oldName = player.getName(); // save before changing
                        String newName = ScannerHelper.getString("New name: ");
                        player.setName(newName);

                        // update DB
                        DataBase dbManager = new DataBase();
                        dbManager.connect();
                        dbManager.updateCharacterNameById(player.getId(), newName);
                        dbManager.close();
                    }
                    //sends the created character to Game class and starts Game
                    case 3 -> {
                        Game game = new Game(player);
                        boolean playerSurvived = game.start();

                        if (!playerSurvived) {
                            boolean postGame = true;
                            while (postGame) {
                                System.out.println("\n--- Game Over Menu ---");
                                System.out.println("1. Restart with same character (reset stats)");
                                System.out.println("2. Change character");
                                System.out.println("3. Quit");

                                int postChoice = ScannerHelper.getInt("Your choice: ");
                                switch (postChoice) {
                                    case 1 -> {
                                        player.resetStats(); // you'll define this
                                        postGame = false;
                                    }
                                    case 2 -> {
                                        player = null;
                                        inSubmenu = false; // go back to main menu
                                        postGame = false;
                                    }
                                    case 3 -> System.exit(0);
                                    default -> System.out.println("Invalid option.");
                                }
                            }
                        }

                    }
                    //sends player back to main menu
                    case 4 -> {
                        DataBase dbManager = new DataBase();
                        dbManager.connect();
                        dbManager.deleteCharacterById(player.getId());
                        dbManager.close();

                        player = null; // remove from memory
                        inSubmenu = false; // go back to main menu
                    }
                    default -> System.out.println("Invalid option.");
                }
            }
        }


    }

}
