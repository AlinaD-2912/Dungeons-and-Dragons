package fr.campus.dungeoncrawler.enemies;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.intarfaces.CanAttack;

public class Warlock extends Enemy implements CanAttack {
    public Warlock() {
        super("Warlock", 9, 2);
    }

    @Override
    public boolean canAttack(Character player) {
        return true;
    }
}
