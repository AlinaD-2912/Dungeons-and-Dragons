package fr.campus.dungeoncrawler.enemies;

public class Enemy {
    private String type;
    private int lifeLevel;
    private int attackLevel;

    public Enemy(String type, int lifeLevel, int attackLevel) {
        this.type = type;
        this.lifeLevel = lifeLevel;
        this.attackLevel = attackLevel;

    }

    public String getType() {
        return type;
    }

    public int getLifeLevel() {
        return lifeLevel;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLifeLevel(int lifeLevel) {
        this.lifeLevel = lifeLevel;
    }

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    @Override
    public String toString() {
        return "Character{" +
                "Type='" + type + '\'' +
                ", LifeLevel=" + lifeLevel +
                ", AttackLevel=" + attackLevel +
                '}';
    }


}
