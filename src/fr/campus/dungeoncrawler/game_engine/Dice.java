package fr.campus.dungeoncrawler.game_engine;

public class Dice {
    public int roll() {
        int randomNum = (int)(Math.random() * 7); //0 to 6
        return randomNum;
    }
}
