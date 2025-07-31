package fr.campus.dungeoncrawler.enemies;

import fr.campus.dungeoncrawler.intarfaces.CanAttackWarriors;

public class Orc extends Enemy implements CanAttackWarriors {
    public Orc() {
        super("Orc", 10, 6);
    }
}
