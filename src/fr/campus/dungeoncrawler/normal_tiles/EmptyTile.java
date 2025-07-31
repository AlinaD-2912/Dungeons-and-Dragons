package fr.campus.dungeoncrawler.normal_tiles;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.enemies.Enemy;
import fr.campus.dungeoncrawler.game_engine.Tile;

public class EmptyTile extends Tile {


    public EmptyTile() {
        this.type = Type.EMPTY;
    }

    @Override
    public boolean onTile(Character player) {
        return false;
    }
}
