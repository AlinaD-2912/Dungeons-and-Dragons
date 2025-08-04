package fr.campus.dungeoncrawler.surprise_tiles;

import fr.campus.dungeoncrawler.normal_tiles.SurpriseTile;

public abstract class DefensiveEquipment extends SurpriseTile {

    private enum type{};
    private int defenceLevel;
    private String name;

    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getDefenseLevel() {
        return defenceLevel;
    }
    public String getName() { return name; }


    public void setDefenseLevel(int defenceLevel) {this.defenceLevel = defenceLevel; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return super.toString();
    }
}
