package fr.campus.dungeoncrawler.enemies;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.Warrior;
import fr.campus.dungeoncrawler.intarfaces.CanAttack;

public class Orc extends Enemy implements CanAttack {
    public Orc() {
        super("Orc", 10, 6);
    }

    @Override
    public boolean canAttack(Character player) {
        return player instanceof Warrior;
    }
}
