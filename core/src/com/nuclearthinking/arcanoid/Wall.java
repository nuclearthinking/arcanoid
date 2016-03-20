package com.nuclearthinking.arcanoid;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 20.03.2016
 * Time: 21:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Wall {

    private int[][] levelMap;

    private ArrayList<List<Brick>> wallRows;

    public Wall(int[][] levelMap) {
        this.levelMap = levelMap;
        wallRows = convertMapToWall(this.levelMap);
    }

    private ArrayList<List<Brick>> convertMapToWall(int[][] levelMap) {
        ArrayList<List<Brick>> wall = new ArrayList<List<Brick>>();
        int x = 0;
        int y = 390;
        for (int i = 0; i < levelMap.length; i++) {
            List<Brick> bricks = new ArrayList<Brick>();
            for (int o = 0; o < levelMap[0].length; o++) {
                bricks.add(new Brick(x, y, BrickType.byId(levelMap[i][o])));
                x += 80;
            }
            wall.add(bricks);
            x = 0;
            y -= 20;
        }
        return wall;
    }

    public ArrayList<List<Brick>> getWallArray() {
        return wallRows;
    }
}
