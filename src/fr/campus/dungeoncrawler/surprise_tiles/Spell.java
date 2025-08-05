package fr.campus.dungeoncrawler.surprise_tiles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Spell extends OffensiveEquipment
{
    public enum SpellType {
        FIREBALL(7),    // adds +7 to attack
        THUNDER(2);     // adds +2 to attack

        private final int attackBoost;
        SpellType(int attackBoost) {
            this.attackBoost = attackBoost;
        }

        public int getAttackBoost() {
            return attackBoost;
        }

    }

    private final Spell.SpellType spellType;

    @JsonCreator
    public Spell(@JsonProperty("spellType") SpellType spellType) {
        this.spellType = spellType;
    }

    public int getAttackBoost() {
        return spellType.getAttackBoost();
    }

    public SpellType getSpellType() {
        return spellType;
    }

    @Override
    public String toString() {
        return spellType.name() + " Attack level : +" + getAttackBoost() ;
    }

}
