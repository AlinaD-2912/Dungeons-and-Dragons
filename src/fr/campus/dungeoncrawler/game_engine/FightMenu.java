package fr.campus.dungeoncrawler.game_engine;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.enemies.Enemy;
public class FightMenu {
    public void startFight (Character player, Enemy enemy) {
        System.out.println("--- Fight Started! ---");
        System.out.println("Enemy: " + enemy.getType());
        System.out.println("Attack Level: " + enemy.getAttackLevel());
        System.out.println("Life Level: " + enemy.getLifeLevel());

        // Example simple logic for now:
        System.out.println("You fight bravely and defeat the " + enemy.getType() + "!");

    }

}
