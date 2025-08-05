package fr.campus.dungeoncrawler.normal_tiles;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.game_engine.Board;
import fr.campus.dungeoncrawler.game_engine.FightMenu;
import fr.campus.dungeoncrawler.game_engine.Tile;
import fr.campus.dungeoncrawler.enemies.Enemy;
import fr.campus.dungeoncrawler.intarfaces.CanAttack;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class EnemyTile extends Tile {

    private Enemy enemy;


    @JsonIgnore
    private Board board;

    public EnemyTile() {
        // for Jackson
    }


    public EnemyTile(Enemy enemy) {
        this.type = Type.ENEMY;
        this.enemy = enemy;
    }

//    public EnemyTile() {
//        this.type = Type.ENEMY;
//    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
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
                    return false;
                }
                case PLAYER_DEAD -> {
                    System.out.println("You have been defeated by the " + enemy.getType() + "...");
                    return true;
                }
                case PLAYER_FLED -> {
                    System.out.println("You fled from the fight! Moving 2 steps back.");
                    board.movePlayerBack(2);
                    return false;
                }
                default -> {
                    return false;
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

    @Override
    public String toString() {
        if (enemy == null) {
            return "EnemyTile { EMPTY }";
        }
        return "EnemyTile { enemy: " + enemy.getType() + ", strength: " + enemy.getAttackLevel() + ", life: " + enemy.getLifeLevel() + " }";
    }

    @Override
    public Type getType() {
        return enemy == null ? Type.EMPTY : Type.ENEMY;
    }

}
