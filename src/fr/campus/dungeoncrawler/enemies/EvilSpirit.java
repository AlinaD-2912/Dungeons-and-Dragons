package fr.campus.dungeoncrawler.enemies;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.Warrior;
import fr.campus.dungeoncrawler.characters.Wizard;
import fr.campus.dungeoncrawler.intarfaces.CanAttack;


public class EvilSpirit extends Enemy implements CanAttack {
    public EvilSpirit() {
        super("Evil Spirit", 15, 7);
    }

    @Override
    public boolean canAttack(Character player) {
        return player instanceof Wizard;
    }
}
