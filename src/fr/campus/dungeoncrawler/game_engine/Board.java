package fr.campus.dungeoncrawler.game_engine;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.enemies.*;
import fr.campus.dungeoncrawler.exceptions.OutOfBoardException;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private final int BOARD_SIZE = 64;
    private final Case[] board = new Case[BOARD_SIZE];
    private int playerPosition = 0;
    private Dice dice;
    private Character player;

    public Board(Character player) {
        this.dice = new Dice();
        this.player = player;

        // Initialising board
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = new Case();
        }

        placeEnemies();
    }

    public void placeEnemies() {
        Enemy[] enemies = {
                new Dragon(), new Goblin(), new Warlock(), new Warlock(), new Warlock()
        };

        int placed = 0;
        while (placed < enemies.length) {
            int position = ThreadLocalRandom.current().nextInt(0, BOARD_SIZE);
            if (board[position].isEmpty() && position != 0) { // avoid player start
                board[position].setEnemy(enemies[placed]);
                placed++;
            }
        }
    }

    public boolean movePlayer() {
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

        Case currentCase = board[playerPosition];
        if (!currentCase.isEmpty()) {
            Enemy enemy = currentCase.getEnemy();
            System.out.println("You encountered a " + enemy.getType() + "!");
            FightMenu menu = new FightMenu();
            boolean playerDead = menu.startFight(player, enemy);

            if (enemy.getLifeLevel() <= 0) {
                System.out.println("The " + enemy.getType() + " has been defeated and removed from the board.");
                currentCase.clear();
            }

            if (playerDead) {
                System.out.println("Game Over.");
                return true;
            }
        }

        return false; // Game continues
    }

    // Separates position logic and throws exception if out of board
    private void updatePlayerPosition(int steps) throws OutOfBoardException {
        playerPosition += steps;
        if (playerPosition >= BOARD_SIZE) {
            throw new OutOfBoardException("You moved beyond the last square!");
        }
    }
}
