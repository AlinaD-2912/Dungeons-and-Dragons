package fr.campus.dungeoncrawler.game_engine;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.enemies.Enemy;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,  // or Id.NAME if you prefer custom names
        include = JsonTypeInfo.As.PROPERTY,
        property = "@class"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = fr.campus.dungeoncrawler.surprise_tiles.Potion.class, name = "Potion"),
        @JsonSubTypes.Type(value = fr.campus.dungeoncrawler.surprise_tiles.Spell.class, name = "Spell"),
        @JsonSubTypes.Type(value = fr.campus.dungeoncrawler.surprise_tiles.Weapon.class, name = "Weapon"),
        // Add others here (EnemyTile, EmptyTile, etc)
})

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

    @Override
    public String toString() {
        return "Tile{" +
                "type=" + type +
                '}';
    }
}
