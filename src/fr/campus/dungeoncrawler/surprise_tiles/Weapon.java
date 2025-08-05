package fr.campus.dungeoncrawler.surprise_tiles;

public class Weapon extends OffensiveEquipment{

    public enum WeaponType {
        SWORD(5),    // adds +5 to attack
        MACE(3);     // adds +3 to attack

        private final int attackBoost;
        WeaponType(int attackBoost) {
            this.attackBoost = attackBoost;
        }

        public int getAttackBoost() {
            return attackBoost;
        }
    }

    private  Weapon.WeaponType weaponType;

    public Weapon(Weapon.WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public Weapon() {
        // can initialize with default value or leave null
    }



    public Weapon.WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Weapon.WeaponType weaponType) {
        this.weaponType = weaponType;
    }


    public int getAttackBoost() {
        return weaponType.getAttackBoost();
    }


    @Override
    public String toString() {
        return weaponType.name() + " Attack level : +" + getAttackBoost() ;
    }
}
