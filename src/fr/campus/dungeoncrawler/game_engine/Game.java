package fr.campus.dungeoncrawler.game_engine;

public class Game {

    private Board board;

    public Game() {
        this.board = new Board();
    }

    public void start() {
        System.out.println("Game started!");
//        board.movePlayer(); // example call
    }

}
