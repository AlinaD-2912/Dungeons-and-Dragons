package fr.campus.dungeoncrawler.game_engine;


import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.db.DataBase;
import fr.campus.dungeoncrawler.normal_tiles.EnemyTile;

public class Game {

    /**
     * Controls the game engine
     * lets the game continue until player has won
     * send created character further to the Board
     * Game sends -> player to -> Board
     */

    private Board board;

//    public Game (Character player)
//    {
//        this.board = new Board(player);
//    }

    public Game(Character player) {
        DataBase db = new DataBase();
        db.connect();

        Tile[] tiles = db.getBoardByPlayer(player.getName());
        db.close();

        if (tiles != null) {
            // Create an empty board or with tiles? You might want a Board constructor accepting tiles:
            this.board = new Board(player, tiles);

            // Set board reference inside EnemyTile (and other tiles if needed)
            for (Tile tile : tiles) {
                if (tile instanceof EnemyTile et) {
                    et.setBoard(this.board);
                }
            }
        } else {
            // No saved board found, create new one
            this.board = new Board(player);
        }
    }

    public Board getBoard() {
        return this.board;
    }


    public boolean start() {
        boolean hasWon = false;

        while (!hasWon) {
            System.out.println("Press Enter to roll the dice...");
            new java.util.Scanner(System.in).nextLine();
            hasWon = board.movePlayer(); // Will return true if won or died
        }

        System.out.println("Game over, thanks for playing!");
        return board.getPlayer().isAlive();
    }

    public void saveGame() {
        DataBase db = new DataBase();
        db.connect();

        Tile[] tiles = board.getTiles();

        String playerName = board.getPlayer().getName();

        db.insertBoard(playerName, tiles);
        db.close();

        System.out.println("Game saved successfully!");
    }


}
