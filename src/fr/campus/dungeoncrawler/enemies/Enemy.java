package fr.campus.dungeoncrawler.enemies;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"   // this is the field Jackson uses to decide the subtype
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Goblin.class, name = "Goblin"),
        @JsonSubTypes.Type(value = Orc.class, name = "Orc"),
        @JsonSubTypes.Type(value = Warlock.class, name = "Warlock"),
        @JsonSubTypes.Type(value = EvilSpirit.class, name = "EvilSpirit"),
        @JsonSubTypes.Type(value = Dragon.class, name = "Dragon")
})

public abstract class Enemy {
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
