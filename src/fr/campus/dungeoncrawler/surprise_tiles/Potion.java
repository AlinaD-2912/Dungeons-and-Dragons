package fr.campus.dungeoncrawler.surprise_tiles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Potion extends DefensiveEquipment {

    public enum PotionType {
        SMALL(2),    // Heals 2 HP
        BIG(5);     // Heals 5 HP

        private final int lifeBoost;
        PotionType(int lifeBoost) {
            this.lifeBoost = lifeBoost;
        }

        public int getLifeBoost() {
            return lifeBoost;
        }
    }

    private final PotionType potionType;
    @JsonCreator
    public Potion(@JsonProperty("type") PotionType potionType) {
        this.potionType = potionType;
    }

    public int getLifeBoost() {
        return potionType.getLifeBoost();
    }

    public PotionType getPotionType() {
        return potionType;
    }


    @Override
    public String toString() {

        return potionType.name() + " potion, Life level boost +" +  getLifeBoost();
    }
}
