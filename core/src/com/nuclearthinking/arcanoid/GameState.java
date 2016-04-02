package com.nuclearthinking.arcanoid;

/**
 * Date: 26.03.2016
 * Time: 22:47
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class GameState {

    private int lifeAmount;
    private int score;


    private static GameState ourInstance = new GameState();

    public static GameState getInstance() {
        return ourInstance;
    }

    private GameState() {
        lifeAmount = 3;
    }

    public int getLifeAmount() {
        return lifeAmount;
    }

    public void loseLife() {
        if (lifeAmount > 0) {
            lifeAmount--;
        } else {
            System.out.println("Game Over");
        }

    }

    public void addPoints() {
        score += 50;
    }

    public String getScoreString() {
        return "score " + score;
    }
}
