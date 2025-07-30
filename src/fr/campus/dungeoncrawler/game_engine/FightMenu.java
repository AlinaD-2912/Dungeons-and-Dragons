package fr.campus.dungeoncrawler.game_engine;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.enemies.Enemy;

public class FightMenu {

    private Enemy enemy;
    private Character player;

    public FightMenu() {

    }

    public boolean startFight (Character player, Enemy enemy) {

        this.player = player;
        this.enemy = enemy;

        System.out.println("--- Fight Started! ---");
        System.out.println("Enemy: " + enemy.getType());
        System.out.println("Attack Level: " + enemy.getAttackLevel());
        System.out.println("Life Level: " + enemy.getLifeLevel());

        System.out.println("You strike first with power: " + player.getAttackLevel());
        enemy.setLifeLevel(enemy.getLifeLevel() - player.getAttackLevel());

        if (enemy.getLifeLevel() <= 0) {
            System.out.println("You defeated the " + enemy.getType() + "!");
            return false;
        }

        System.out.println("The " + enemy.getType() + " hits back with power: " + enemy.getAttackLevel());
        player.setLifeLevel(player.getLifeLevel() - enemy.getAttackLevel());

        System.out.println("The " + enemy.getType() + " flees after the attack!");

        System.out.println("Your remaining health: " + player.getLifeLevel());

        if (player.getLifeLevel() <= 0) {
            System.out.println("You have been defeated...");
            return true; // Player is dead
        }

        return false;


    }

}
