package fr.campus.dungeoncrawler.surprise_tiles;

import fr.campus.dungeoncrawler.normal_tiles.SurpriseTile;

public abstract class OffensiveEquipment extends SurpriseTile {

    private enum type{};
    private int attackLevel;
    private String name;

    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getAttackLevel() {
        return attackLevel;
    }
    public String getName() { return name; }


    public void setAttackLevel(int attackLevel) {this.attackLevel = attackLevel; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return super.toString();
    }

}
