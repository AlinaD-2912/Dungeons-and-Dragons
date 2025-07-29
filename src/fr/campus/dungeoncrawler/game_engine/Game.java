package fr.campus.dungeoncrawler.game_engine;

public class Game {

    private Board board;

    public Game() {
        this.board = new Board();
    }

    public void start() {
        boolean hasWon = false;

        while (!hasWon) {
            System.out.println("Press Enter to roll the dice...");
            new java.util.Scanner(System.in).nextLine(); // wait for user input
            hasWon = board.movePlayer(); // check win after move
        }

        System.out.println("Game over, thanks for playing!");
    }

}
