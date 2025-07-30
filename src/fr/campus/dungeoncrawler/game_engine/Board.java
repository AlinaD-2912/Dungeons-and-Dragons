package fr.campus.dungeoncrawler.game_engine;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.enemies.*;
import fr.campus.dungeoncrawler.exceptions.OutOfBoardException;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    /**
     * Board is responsible for organising everything that happens on the board
     * it determines the number of total cases
     * if player meats with enemy it sends player and enemy to class FightMenu to start the fight
     *
     * Manages players movements
     * Decides where to place enemies
     * Updates player position
     * Creates and assigns case types
     * Decides where to place cases
     *
     * Board sends -> enemy, player to -> class FightMenu and starts a fight
     * Board rolls -> dice from -> class Dice and sends result back to -> Board movePlayer()
     * Board creates -> enemies and places them on -> Board placeEnemies()
     */
    private final int BOARD_SIZE = 64;
    private final Case[] board = new Case[BOARD_SIZE];
    private int playerPosition = 0;
    private Dice dice;
    private Character player;

    public Board(Character player) {
        //Assigns the values to empty objects
        this.dice = new Dice();
        this.player = player;

        // Initialising board
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = new Case();
        }
        // Places enemies
        placeEnemies();
    }

    public void placeEnemies() {
        //determines how many enemies to create and place on board
        Enemy[] enemies = {
                new Dragon(), new Goblin(), new Warlock(), new Warlock(), new Warlock()
        };

        int placed = 0;
        //place all the enemies present in te table
        while (placed < enemies.length) {
            int position = ThreadLocalRandom.current().nextInt(0, BOARD_SIZE);
            if (board[position].isEmpty() && position != 0) { // avoid player start
                board[position].setEnemy(enemies[placed]);
                placed++;
            }
        }
    }

    public boolean movePlayer() {
        //recover the result of dice
        int steps = dice.roll();

        System.out.println("You rolled: " + steps);
        try {
            updatePlayerPosition(steps);
        } catch (OutOfBoardException e) {
            System.out.println(e.getMessage());
            System.out.println("Game Over.");
            return true;
        }

        System.out.println("Your new position: " + playerPosition);
        //sends the current position of player on the board to the class Case
        Case currentCase = board[playerPosition];
        if (!currentCase.isEmpty()) {
            Enemy enemy = currentCase.getEnemy();
            System.out.println("You encountered a " + enemy.getType() + "!");

            //sends the signal to class Fight menu to start the fight with the enemy
            FightMenu menu = new FightMenu();

            //boolean takes the result from class fightmenu to know if player died or survived during the fight
            boolean playerDead = menu.startFight(player, enemy);

            //if enemy dies
            if (enemy.getLifeLevel() <= 0) {
                System.out.println("The " + enemy.getType() + " has been defeated and removed from the board.");
                currentCase.clear();
            }
            //if player dies
            if (playerDead) {
                System.out.println("Game Over.");
                return true;
            }
        }

        return false; // Game continues
    }

    // Separates position logic and throws exception if out of board

    private void updatePlayerPosition(int steps) throws OutOfBoardException {
        //updates the player position
        playerPosition += steps;

        if (playerPosition >= BOARD_SIZE) {
            throw new OutOfBoardException("You moved beyond the last square!");
        }
    }
}
