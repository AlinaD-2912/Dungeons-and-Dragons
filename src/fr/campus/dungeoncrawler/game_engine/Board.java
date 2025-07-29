package fr.campus.dungeoncrawler.game_engine;

public class Board {
    int totalCases = 64;
    private int playerPosition = 0;
    private Dice dice;

    public Board() {
        this.dice = new Dice();
    }

    public boolean movePlayer() {
        int steps = dice.roll();
        playerPosition += steps;
        System.out.println("You rolled: " + steps);
        System.out.println("Your new position: " + playerPosition);

        if (playerPosition >= totalCases) {
            System.out.println("🏆 You reached the end! You win!");
            return true; // Game over
        }

        return false;


    }
}
