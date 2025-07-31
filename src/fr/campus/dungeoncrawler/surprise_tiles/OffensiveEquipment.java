package fr.campus.dungeoncrawler.surprise_tiles;

import fr.campus.dungeoncrawler.normal_tiles.SurpriseTile;

public abstract class OffensiveEquipment extends SurpriseTile {

    private enum type{};
    private int attackLevel;
    private String name;

    public int getAttackLevel() {
        return attackLevel;
    }
    public String getName() { return name; }


    public void setAttackLevel(int attackLevel) {this.attackLevel = attackLevel; }
    public void setName(String name) { this.name = name; }

}
