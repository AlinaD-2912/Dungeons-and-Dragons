package fr.campus.dungeoncrawler.surprise_tiles;

import fr.campus.dungeoncrawler.game_engine.Tile;

public class Potion extends DefensiveEquipment {

    public enum Type {
        SMALL(2),    // Heals 2 HP
        BIG(5);     // Heals 5 HP

        private final int lifeBoost;
        Type(int lifeBoost) {
            this.lifeBoost = lifeBoost;
        }

        public int getLifeBoost() {
            return lifeBoost;
        }
    }

    private final Type type;

    public Potion(Type type) {
        this.type = type;
    }

    public int getLifeBoost() {
        return type.getLifeBoost();
    }

    @Override
    public String toString() {
        return type.name();
    }
}
