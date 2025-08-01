package fr.campus.dungeoncrawler.normal_tiles;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.game_engine.Board;
import fr.campus.dungeoncrawler.game_engine.FightMenu;
import fr.campus.dungeoncrawler.game_engine.Tile;
import fr.campus.dungeoncrawler.enemies.Enemy;
import fr.campus.dungeoncrawler.intarfaces.CanAttack;

public class EnemyTile extends Tile {

    private Enemy enemy;
    private Board board;

    public EnemyTile(Enemy enemy) {
        this.type = Type.ENEMY;
        this.enemy = enemy;
        this.board = board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


    public Enemy getEnemy() {
        return enemy;
    }

    @Override
    public boolean onTile(Character player) {
        System.out.println("You encountered a " + enemy.getType() + "!");

        if (enemy instanceof CanAttack attacker && attacker.canAttack(player)) {
            FightMenu menu = new FightMenu();
            FightMenu.FightResult result = menu.startFight(player, enemy);

            switch (result) {
                case ENEMY_DEAD -> {
                    System.out.println("The " + enemy.getType() + " has been defeated and removed from the board.");
                    removeEnemy();
                    return false; // Player survived, continue game
                }
                case PLAYER_DEAD -> {
                    System.out.println("You have been defeated by the " + enemy.getType() + "...");
                    return true; // Signal player dead - maybe end game or respawn
                }
                case PLAYER_FLED -> {
                    System.out.println("You fled from the fight! Moving 2 steps back.");
                    // Call your logic to move player 2 steps back here, e.g.:
                    board.movePlayerBack(2);

                    return false; // Player fled, but alive
                }
                default -> {
                    return false; // Should not happen, but safe fallback
                }
            }

        } else {
            System.out.println("The " + enemy.getType() + " ignores the player.");
            return false;
        }
    }

    public void removeEnemy() {
        this.enemy = null;
        this.type = Type.EMPTY;
    }
}
