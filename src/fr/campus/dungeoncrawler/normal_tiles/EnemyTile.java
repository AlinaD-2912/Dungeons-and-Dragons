package fr.campus.dungeoncrawler.normal_tiles;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.game_engine.FightMenu;
import fr.campus.dungeoncrawler.game_engine.Tile;
import fr.campus.dungeoncrawler.enemies.Enemy;
import fr.campus.dungeoncrawler.intarfaces.CanAttack;

public class EnemyTile extends Tile {

    private Enemy enemy;

    public EnemyTile(Enemy enemy) {
        this.type = Type.ENEMY;
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    @Override
    public boolean onTile(Character player) {
        System.out.println("You encountered a " + enemy.getType() + "!");

        if (enemy instanceof CanAttack attacker && attacker.canAttack(player)) {
            FightMenu menu = new FightMenu();
            boolean playerDead = menu.startFight(player, enemy);

            if (enemy.getLifeLevel() <= 0) {
                System.out.println("The " + enemy.getType() + " has been defeated and removed from the board.");
                removeEnemy();
            }

            return playerDead;
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
