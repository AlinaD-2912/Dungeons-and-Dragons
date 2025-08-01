package fr.campus.dungeoncrawler.enemies;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.intarfaces.CanAttack;

public class Goblin extends Enemy implements CanAttack {
    public Goblin() {
        super("Goblin", 6, 1);
    }

    @Override
    public boolean canAttack(Character player) {
        return true;
    }
}
