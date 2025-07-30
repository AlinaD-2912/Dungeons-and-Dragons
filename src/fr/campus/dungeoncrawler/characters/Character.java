package fr.campus.dungeoncrawler.characters;

public abstract class Character {

    private String type;
    private String name;
    private int lifeLevel;
    private int attackLevel;
    private String weapon;

    //Constructor
    public Character(String type, String name, int lifeLevel, int attackLevel, String weapon) {
        this.type = type;
        this.name = name;
        this.lifeLevel = lifeLevel;
        this.attackLevel = attackLevel;
        this.weapon = weapon;
    }

    //Getters
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getLifeLevel() {
        return lifeLevel;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public String getWeapon() {
        return weapon;
    }

    //Setters
    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLifeLevel(int lifeLevel) {
        this.lifeLevel = lifeLevel;
    }

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    //ToString
    @Override
    public String toString() {
        return "Character{" +
                "Type='" + type + '\'' +
                ", Name='" + name + '\'' +
                ", LifeLevel=" + lifeLevel +
                ", AttackLevel=" + attackLevel +
                ", Weapon='" + weapon + '\'' +
                '}';
    }
}
