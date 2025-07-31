package fr.campus.dungeoncrawler.surprise_tiles;

public class Weapon extends OffensiveEquipment{

    public enum Type {
        SWORD(5),    // adds +5 to attack
        MACE(3);     // adds +3 to attack

        private final int attackBoost;
        Type(int attackBoost) {
            this.attackBoost = attackBoost;
        }

        public int getAttackBoost() {
            return attackBoost;
        }
    }

    private final Weapon.Type type;

    public Weapon(Weapon.Type type) {
        this.type = type;
    }

    public int getAttackBoost() {
        return type.getAttackBoost();
    }


    @Override
    public String toString() {
        return type.name() + " Attack level : +" + getAttackBoost() ;
    }
}
