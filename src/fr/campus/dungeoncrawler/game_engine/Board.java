package fr.campus.dungeoncrawler.game_engine;
import fr.campus.dungeoncrawler.enemies.Enemy;

public class Board {
    int totalCases = 64;
    private int playerPosition = 0;
    private Dice dice;

    private int[] enemyPosition = new int [3];
    private Enemy[] enemies = new Enemy[3];

    public Board() {
        this.dice = new Dice();
        placeEnemies();
    }

    public void placeEnemies () {

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

        return false;


    }
}
