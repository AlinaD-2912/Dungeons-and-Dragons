package fr.campus.dungeoncrawler.game_engine;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.enemies.Dragon;
import fr.campus.dungeoncrawler.enemies.Enemy;
import fr.campus.dungeoncrawler.enemies.Goblin;
import fr.campus.dungeoncrawler.enemies.Warlock;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    int totalCases = 64;
    private int playerPosition = 0;
    private Dice dice;
    private Character player;


    private int[] enemyPosition = new int[5];
    private Enemy[] enemies = new Enemy[5];

    public Board(Character player) {
        this.dice = new Dice();
        this.player = player;
        placeEnemies();

    }

    public void placeEnemies() {
        enemyPosition[0] = ThreadLocalRandom.current().nextInt(0, totalCases);
        enemies[0] = new Dragon();

        do {
            enemyPosition[1] = ThreadLocalRandom.current().nextInt(0, totalCases);
        } while (enemyPosition[1] == enemyPosition[0]);
        enemies[1] = new Goblin();

        do {
            enemyPosition[2] = ThreadLocalRandom.current().nextInt(0, totalCases);
        } while (enemyPosition[2] == enemyPosition[0] || enemyPosition[2] == enemyPosition[1]);
        enemies[2] = new Warlock();

        do {
            enemyPosition[3] = ThreadLocalRandom.current().nextInt(0, totalCases);
        } while (enemyPosition[3] == enemyPosition[0] || enemyPosition[3] == enemyPosition[1] || enemyPosition[3] == enemyPosition[2]);
        enemies[3] = new Warlock();

        do {
            enemyPosition[4] = ThreadLocalRandom.current().nextInt(0, totalCases);
        } while (enemyPosition[4] == enemyPosition[0] || enemyPosition[4] == enemyPosition[1] || enemyPosition[4] == enemyPosition[2]);
        enemies[4] = new Warlock();

    }

    public boolean movePlayer() {
        int steps = dice.roll();
        playerPosition += steps;
        System.out.println("You rolled: " + steps);
        System.out.println("Your new position: " + playerPosition);

        if (playerPosition >= totalCases) {
            System.out.println(" You reached the end! You win!");
            return true; // Game over
        }

        for (int i = 0; i < enemyPosition.length; i++) {
            if (playerPosition == enemyPosition[i]) {
                System.out.println("You encountered a " + enemies[i].getType() + "!");
                FightMenu menu = new FightMenu();
                menu.startFight(player, enemies[i]);
                // Call fight logic if needed
            }
        }

        return false;

    }
}
