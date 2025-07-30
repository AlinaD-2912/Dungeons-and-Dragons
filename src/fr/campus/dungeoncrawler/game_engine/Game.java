package fr.campus.dungeoncrawler.game_engine;

import fr.campus.dungeoncrawler.characters.Character;

public class Game {

    private Board board;

    public Game (Character player)
    {
        this.board = new Board(player);
    }

    public void start() {
        boolean hasWon = false;

        while (!hasWon) {
            System.out.println("Press Enter to roll the dice...");
            new java.util.Scanner(System.in).nextLine(); // creates user input attached to keyboard, nextline waits for user to press some key
            hasWon = board.movePlayer(); // check win after move
        }

        System.out.println("Game over, thanks for playing!");
    }

}
