package fr.campus.dungeoncrawler.game_engine;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.enemies.*;
//import fr.campus.dungeoncrawler.exceptions.OutOfBoardException;
import fr.campus.dungeoncrawler.intarfaces.CanUseSpells;
import fr.campus.dungeoncrawler.intarfaces.CanUseWeapons;
import fr.campus.dungeoncrawler.normal_tiles.EmptyTile;
import fr.campus.dungeoncrawler.normal_tiles.EnemyTile;
import fr.campus.dungeoncrawler.surprise_tiles.Potion;
import fr.campus.dungeoncrawler.surprise_tiles.Spell;
import fr.campus.dungeoncrawler.surprise_tiles.Weapon;

import java.util.concurrent.ThreadLocalRandom;


public class Board {
    /**
     * Board is responsible for organising everything that happens on the board
     * it determines the number of total tiles
     * if player meats with enemy it sends player and enemy to class FightMenu to start the fight
     * ...
     * Manages players movements
     * Decides where to place enemies
     * Updates player position
     * Creates and assigns tile types
     * Decides where to place surprise tiles
     * ...
     * Board sends -> enemy, player to -> class FightMenu and starts a fight
     * Board rolls -> dice from -> class Dice and sends result back to -> Board movePlayer()
     * Board creates -> enemies and places them on -> Board placeEnemies()
     */

    private final int BOARD_SIZE = 100;
    //Create an array that can hold 64 references to Tile objects
    private final Tile[] board = new Tile[BOARD_SIZE];
    private int playerPosition = 0;
    private Dice dice;
    private Character player;


        public Board(Character player) {

        //Assigns the values to empty objects
        this.dice = new Dice();
        this.player = player;

        // Initialising board
        //Creates actual instances of the subclasses and putting them into the array.
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = new EmptyTile();
        }

        // Places enemies
        placeEnemies();
        //Place surprise Tiles
        placeSurpriseTiles();
        printBoard();
    }
    public Board(Character player, Tile[] loadedTiles) {
        this.player = player;
        this.dice = new Dice();

        if (loadedTiles == null || loadedTiles.length != BOARD_SIZE) {
            // Defensive fallback - if loadedTiles is null or wrong size,
            // initialize empty board as usual
            for (int i = 0; i < BOARD_SIZE; i++) {
                board[i] = new EmptyTile();
            }
            placeEnemies();
            placeSurpriseTiles();
        } else {
            // Use loaded tiles directly
            for (int i = 0; i < BOARD_SIZE; i++) {
                board[i] = loadedTiles[i];
                // If tile is EnemyTile, set its board reference
                if (board[i] instanceof EnemyTile enemyTile) {
                    enemyTile.setBoard(this);
                }
            }
        }

        // Initialize player position (optional, can be stored/restored as well)
        playerPosition = 0;  // Or restore from DB if you save it
        printBoard();
    }

    public void printBoard() {
        System.out.println("\n=== BOARD OVERVIEW ===");
        for (int i = 0; i < board.length; i++) {
            Tile tile = board[i];
            String display;

            if (tile instanceof EmptyTile) {
                display = "[   ]";
            } else if (tile instanceof EnemyTile) {
                display = "[ E ]";
            } else if (tile instanceof Potion) {
                display = "[ P ]";
            } else if (tile instanceof Spell) {
                display = "[ S ]";
            } else if (tile instanceof Weapon) {
                display = "[ W ]";
            } else {
                display = "[ ? ]"; // Unknown or undefined tile
            }

            System.out.print(display);

            // Optional: Line break every 8 tiles (or any size you want)
            if ((i + 1) % 8 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }


    public Character getPlayer() {
        return player;
    }


    public void placeEnemies() {
        //determines how many enemies to create and place on board
        Enemy[] enemies = {
                // 4 dragons
                new Dragon(), new Dragon(), new Dragon(), new Dragon(),
                // 10 warlocks
                new Warlock(), new Warlock(), new Warlock(), new Warlock(), new Warlock(),
                new Warlock(), new Warlock(), new Warlock(), new Warlock(), new Warlock(),
                // 10 goblins
                new Goblin(), new Goblin(), new Goblin(), new Goblin(), new Goblin(),
                new Goblin(), new Goblin(), new Goblin(), new Goblin(), new Goblin(),
                // 5 evil spirits
                new EvilSpirit(), new EvilSpirit(), new EvilSpirit(), new EvilSpirit(), new EvilSpirit(),
                // 5 orcs
                new Orc(), new Orc(), new Orc(), new Orc(), new Orc()
        };

        int placed = 0;

        //place all the enemies present on the board
        while (placed < enemies.length) {
            int position = ThreadLocalRandom.current().nextInt(1, BOARD_SIZE); // avoid start at 0

            //Checks whether the object stored at board[position] is an instance of the EmptyTile class
            if (board[position] instanceof EmptyTile) {
                board[position] = new EnemyTile(enemies[placed]);
                ((EnemyTile) board[position]).setBoard(this);
                placed++;
            }
        }
    }


    public boolean movePlayer() {
        int steps = dice.roll();
        System.out.println("You rolled: " + steps);

        boolean playerWon = updatePlayerPosition(steps);
        if (playerWon) {
            return true; // triggers game end in Game.start()
        }

        // potion boost
        if (board[playerPosition] instanceof Potion potion) {
            System.out.println("You found a " + potion.toString() + " potion!");
            player.increaseLifeLevel(potion.getLifeBoost());

            // Optionally remove potion
            board[playerPosition] = new EmptyTile();
        }

        // spell boost
        if (board[playerPosition] instanceof Spell spell) {
            if (player instanceof CanUseSpells) {
                System.out.println("You found a " + spell.toString() + " spell!");
                player.increaseAttackLevel(spell.getAttackBoost());

                // Optionally remove spell
                board[playerPosition] = new EmptyTile();
            } else {
                System.out.println("You found a spell, but you can't use it.");
            }

        }


        // weapon boost
        if (board[playerPosition] instanceof Weapon weapon) {
            if (player instanceof CanUseWeapons) {
                System.out.println("You found a " + weapon.toString() + " weapon!");
                player.increaseAttackLevel(weapon.getAttackBoost());

                // Optionally remove weapon
                board[playerPosition] = new EmptyTile();
            } else {
                System.out.println("You found a weapon, but you can't use it.");
            }

        }


        System.out.println("Your new position: " + playerPosition);
        //sends the current position of player on the board to the class Tile
        Tile currentTile = board[playerPosition];
        return currentTile.onTile(player);
    }

    public void movePlayerBack(int steps) {
        boolean playerWon = updatePlayerPosition(-steps);
        if (playerWon) {
            System.out.println("You fled but somehow moved beyond board end? Check logic.");
        }
    }


    public void placeSurpriseTiles() {
        Tile[] surpriseTiles = {
                // 5 maces
                new Weapon(Weapon.WeaponType.MACE), new Weapon(Weapon.WeaponType.MACE), new Weapon(Weapon.WeaponType.MACE),
                new Weapon(Weapon.WeaponType.MACE), new Weapon(Weapon.WeaponType.MACE),
                // 4 swords
                new Weapon(Weapon.WeaponType.SWORD), new Weapon(Weapon.WeaponType.SWORD), new Weapon(Weapon.WeaponType.SWORD),
                new Weapon(Weapon.WeaponType.SWORD),
                // 5 thunders
                new Spell(Spell.SpellType.THUNDER), new Spell(Spell.SpellType.THUNDER), new Spell(Spell.SpellType.THUNDER),
                new Spell(Spell.SpellType.THUNDER), new Spell(Spell.SpellType.THUNDER),
                // 2 fireballs
                new Spell(Spell.SpellType.FIREBALL), new Spell(Spell.SpellType.FIREBALL),
                // 6 small potions
                new Potion(Potion.PotionType.SMALL), new Potion(Potion.PotionType.SMALL), new Potion(Potion.PotionType.SMALL),
                new Potion(Potion.PotionType.SMALL), new Potion(Potion.PotionType.SMALL), new Potion(Potion.PotionType.SMALL),
                // 2 big potions
                new Potion(Potion.PotionType.BIG), new Potion(Potion.PotionType.BIG),

        };

        int placed = 0;

        //place all the enemies present on the board
        while (placed < surpriseTiles.length) {
            int position = ThreadLocalRandom.current().nextInt(1, BOARD_SIZE); // skip start at 0

            if (board[position] instanceof EmptyTile) {
                board[position] = surpriseTiles[placed]; // No need for new SurpriseTile()
                placed++;
            }
        }
    }


    // Separates position logic and throws exception if out of board
    private boolean updatePlayerPosition(int steps) {
        playerPosition += steps;

        if (playerPosition >= BOARD_SIZE) {
            System.out.println("You moved beyond the last square!");
            player.setLifeLevel(0); // Mark as dead → triggers Game Over menu
            return true; // Player moved beyond the board — game ends
        }

        if (playerPosition < 0) {
            playerPosition = 0;  // clamp to start of board
            System.out.println("You can't move beyond the start of the board. Position reset to 0.");
        }

        return false;
    }

    public Tile[] getTiles() {
        return this.board;
    }


}
