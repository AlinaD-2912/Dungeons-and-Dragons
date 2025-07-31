package fr.campus.dungeoncrawler.characters;

public abstract class Character {

    private String type;
    private String name;
    private int lifeLevel;
    private int attackLevel;
    private String weapon;

    private final int baseAttack;
    private final int baseLife;


    //Constructor
    public Character(String type, String name, int lifeLevel, int attackLevel, String weapon) {
        this.type = type;
        this.name = name;
        this.lifeLevel = lifeLevel;
        this.attackLevel = attackLevel;
        this.weapon = weapon;

        this.baseAttack = attackLevel;
        this.baseLife = lifeLevel;
    }

    // reset character after game over or player won
    public void resetStats() {
        this.attackLevel = baseAttack;
        this.lifeLevel = baseLife;
    }

    //increase the life level after player picked the potion
    public void increaseLifeLevel(int amount) {
        this.lifeLevel += amount;
        System.out.println("You gained " + amount + " life points! Current life: " + this.lifeLevel);
    }


    public void increaseAttackLevel(int amount) {
        this.attackLevel += amount;
        System.out.println("You gained " + amount + " attack points! Current attack level: " + this.attackLevel);
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

    public boolean isAlive() {
        return lifeLevel > 0;
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
