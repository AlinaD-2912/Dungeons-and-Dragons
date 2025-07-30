package fr.campus.dungeoncrawler.game_engine;
import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    public int roll() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 6);
        return randomNum;
    }
}
