package fr.campus.dungeoncrawler.game_engine;

import fr.campus.dungeoncrawler.characters.Character;

public class Game {

    /**
     * Controls the game engine
     * lets the game continue until player has won
     * send created character further to the Board
     * Game sends -> player to -> Board
     */

    private Board board;

    public Game (Character player)
    {
        this.board = new Board(player);
    }

    public boolean start() {
        boolean hasWon = false;

        while (!hasWon) {
            System.out.println("Press Enter to roll the dice...");
            new java.util.Scanner(System.in).nextLine();
            hasWon = board.movePlayer(); // Will return true if won or died
        }

        System.out.println("Game over, thanks for playing!");
        return board.getPlayer().isAlive(); // returns true if player survived
    }

}
