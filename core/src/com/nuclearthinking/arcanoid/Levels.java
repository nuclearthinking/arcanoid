package com.nuclearthinking.arcanoid;

import java.util.ArrayList;

/**
 * Date: 24.04.2016
 * Time: 8:44
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Levels {

    private ArrayList<int[][]> levels;
    int currentLevel;

    private Levels() {
        levels = new ArrayList<>();
        combineLevels();
        currentLevel = 0;
    }

    void combineLevels() {
        levels.add(Level.LEVEL1);
        levels.add(Level.LEVEL2);
    }


    public boolean hasNext() {
        return currentLevel < levels.size();
    }

    public int[][] next() {
        int[][] level = levels.get(currentLevel);
        currentLevel++;
        return level;
    }

    private static Levels ourInstance = new Levels();

    public static Levels getInstance() {
        return ourInstance;
    }


}
