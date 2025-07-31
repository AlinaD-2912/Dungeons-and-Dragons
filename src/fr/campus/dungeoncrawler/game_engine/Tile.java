package fr.campus.dungeoncrawler.game_engine;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.enemies.Enemy;

public abstract class Tile {
    /**
     * Defines what happens when player arrives at tile
     */
    public enum Type {
        ENEMY,
        EMPTY,
        SURPRISE,
        POTION,
    }
    protected Type type;

    public Type getType() {
        return type;
    }

    public abstract boolean onTile(Character player);
}
