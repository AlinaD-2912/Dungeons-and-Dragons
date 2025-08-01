package fr.campus.dungeoncrawler.enemies;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.Wizard;
import fr.campus.dungeoncrawler.intarfaces.CanAttack;

public class Dragon extends Enemy implements CanAttack {

    public Dragon() {
        super("Dragon", 15, 4);
    }

    @Override
    public boolean canAttack(Character player) {
        return true;
    }

}
