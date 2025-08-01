package fr.campus.dungeoncrawler.characters;

import fr.campus.dungeoncrawler.intarfaces.CanUseWeapons;

import java.util.concurrent.ThreadLocalRandom;

public class Warrior extends Character implements CanUseWeapons {
    public Warrior(String name) {
        super(
                -1,
                "Warrior",
                name,
                ThreadLocalRandom.current().nextInt(5, 10),   // life level
                ThreadLocalRandom.current().nextInt(5, 10),   // attack level
                null,
                null
        );
    }
}
