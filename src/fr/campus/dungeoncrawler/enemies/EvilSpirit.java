package fr.campus.dungeoncrawler.enemies;

import fr.campus.dungeoncrawler.intarfaces.CanAttackWizards;

public class EvilSpirit extends Enemy implements CanAttackWizards {
    public EvilSpirit() {
        super("Evil Spirit", 15, 7);
    }
}
