package fr.campus.dungeoncrawler.game_engine;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.enemies.Enemy;

public class FightMenu {
    /**
     * Fight menu responsible for fight dialog and fight overall
     * it returns true or false back to board in movePlayer() and stocks in boolean
     * FightMenu takes -> player, enemy, make them fight and returns the result of the fight back to -> Board movePlayer()
     */
    private Enemy enemy;
    private Character player;

    public boolean startFight (Character player, Enemy enemy) {

        /**
         * improve so the enemies fight to death and player has a choice to flee so he moves 2 points back
         */
        this.player = player;
        this.enemy = enemy;
        System.out.println(" ");
        System.out.println("-------- Fight Started! -----------");
        System.out.println(" ");
        System.out.println("-------- ENEMY -----------");
        System.out.println("Enemy: " + enemy.getType());
        System.out.println("Attack Level: " + enemy.getAttackLevel());
        System.out.println("Life Level: " + enemy.getLifeLevel());

        System.out.println(" ");
        System.out.println("-------- YOU -----------");
        System.out.println("Attack Level: " + player.getAttackLevel());
        System.out.println("Life Level: " + player.getLifeLevel());

        System.out.println(" ");
        System.out.println("You strike first with power: " + player.getAttackLevel());
        enemy.setLifeLevel(enemy.getLifeLevel() - player.getAttackLevel());
        System.out.println(" ");
        if (enemy.getLifeLevel() <= 0) {
            System.out.println("You defeated the " + enemy.getType() + "!");
            return false;
        }
        System.out.println(" ");
        System.out.println("The " + enemy.getType() + " hits back with power: " + enemy.getAttackLevel());
        player.setLifeLevel(player.getLifeLevel() - enemy.getAttackLevel());
        System.out.println(" ");
        System.out.println("The " + enemy.getType() + " flees after the attack!");
        System.out.println(" ");
        System.out.println("Your remaining health: " + player.getLifeLevel());
        System.out.println(" ");
        if (player.getLifeLevel() <= 0) {
            System.out.println("You have been defeated...");
            return true; // Player is dead
        }

        return false;


    }

}
