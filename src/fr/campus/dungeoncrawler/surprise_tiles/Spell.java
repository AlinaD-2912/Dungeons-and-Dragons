package fr.campus.dungeoncrawler.surprise_tiles;

public class Spell extends OffensiveEquipment
{
    public enum Type {
        FIREBALL(7),    // adds +7 to attack
        THUNDER(2);     // adds +2 to attack

        private final int attackBoost;
        Type(int attackBoost) {
            this.attackBoost = attackBoost;
        }

        public int getAttackBoost() {
            return attackBoost;
        }

    }

    private final Spell.Type type;

    public Spell(Spell.Type type) {
        this.type = type;
    }

    public int getAttackBoost() {
        return type.getAttackBoost();
    }

    @Override
    public String toString() {
        return type.name();
    }

}
