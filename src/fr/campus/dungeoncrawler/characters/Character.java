package fr.campus.dungeoncrawler.characters;

public abstract class Character {

    private String type;
    private String name;
    private int lifeLevel;
    private int attackLevel;
    private String offensiveEquipment;
    private String defensiveEquipment;

    private final int baseAttack;
    private final int baseLife;

    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //Constructor
    public Character(int id, String type, String name, int lifeLevel, int attackLevel,
                     String offensiveEquipment, String defensiveEquipment) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.lifeLevel = lifeLevel;
        this.attackLevel = attackLevel;
        this.offensiveEquipment = offensiveEquipment;
        this.defensiveEquipment = defensiveEquipment;

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

    public String getOffensiveEquipment() { return offensiveEquipment; }
    public String getDefensiveEquipment() { return defensiveEquipment; }


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
    public void setOffensiveEquipment(String equipment) { this.offensiveEquipment = equipment; }
    public void setDefensiveEquipment(String equipment) { this.defensiveEquipment = equipment; }

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
                ", OffensiveEquipment='" + offensiveEquipment + '\'' +
                ", DefensiveEquipment='" + defensiveEquipment + '\'' +
                '}';
    }
}
