package fr.campus.dungeoncrawler.game_engine;

import fr.campus.dungeoncrawler.enemies.Enemy;

public abstract class Case {
    /**
     * Defines what happens when player arrives on case
     */
    private Enemy enemy;

    public boolean isEmpty() {
        return enemy == null;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public void clear() {
        this.enemy = null;
    }
}
