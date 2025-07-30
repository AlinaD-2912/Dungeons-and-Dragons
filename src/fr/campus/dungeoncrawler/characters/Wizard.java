package fr.campus.dungeoncrawler.characters;

import java.util.concurrent.ThreadLocalRandom;

public class Wizard extends Character {
    public Wizard(String name) {
        super("Wizard",
                name,
                ThreadLocalRandom.current().nextInt(3, 6), //life level
                ThreadLocalRandom.current().nextInt(8, 15),
                null
        );
    }
}
