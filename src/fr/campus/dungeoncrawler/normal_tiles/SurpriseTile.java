package fr.campus.dungeoncrawler.normal_tiles;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.game_engine.Tile;

public abstract class SurpriseTile extends Tile {

    private String surpriseType;

    public SurpriseTile(String surpriseType) {
        this.type = Type.SURPRISE;
        this.surpriseType = surpriseType;
    }

    protected SurpriseTile() {
    }

    public String getSurpriseType() {
        return surpriseType;
    }

    @Override
    public boolean onTile(Character player) {
        return false;
    }

    @Override
    public String toString() {
        return "This tile type is : " + this.type;
    }
}
