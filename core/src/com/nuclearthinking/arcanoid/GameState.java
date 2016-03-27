package com.nuclearthinking.arcanoid;

/**
 * Date: 26.03.2016
 * Time: 22:47
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class GameState {

    private int lifeAmount;


    private static GameState ourInstance = new GameState();

    public static GameState getInstance() {
        return ourInstance;
    }

    private GameState() {
    }
}
