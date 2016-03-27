package com.nuclearthinking.arcanoid.objects;

import com.nuclearthinking.arcanoid.BrickType;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 20.03.2016
 * Time: 21:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Wall {

    private final ArrayList<List<Brick>> wallRows;

    public Wall(int[][] levelMap) {
        int[][] levelMap1 = levelMap;
        wallRows = convertMapToWall(levelMap1);
    }

    private ArrayList<List<Brick>> convertMapToWall(int[][] levelMap) {
        ArrayList<List<Brick>> wall = new ArrayList<List<Brick>>();
        int x = 0;
        int y = 390;
        for (int[] aLevelMap : levelMap) {
            List<Brick> bricks = new ArrayList<Brick>();
            for (int o = 0; o < levelMap[0].length; o++) {
                bricks.add(new Brick(x, y, BrickType.byId(aLevelMap[o])));
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
