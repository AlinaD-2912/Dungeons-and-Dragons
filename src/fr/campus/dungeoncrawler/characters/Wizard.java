package fr.campus.dungeoncrawler.characters;

import fr.campus.dungeoncrawler.intarfaces.CanUseSpells;
import fr.campus.dungeoncrawler.surprise_tiles.Spell;

import java.util.concurrent.ThreadLocalRandom;

public class Wizard extends Character implements CanUseSpells {
    public Wizard(String name) {
        super(
                -1,
                "Wizard",
                name,
                ThreadLocalRandom.current().nextInt(3, 6),   // life level
                ThreadLocalRandom.current().nextInt(8, 15),   // attack level
                null,
                null
        );
    }


}
