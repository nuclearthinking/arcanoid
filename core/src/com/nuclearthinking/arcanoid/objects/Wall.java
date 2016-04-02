package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
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
    World world;

    public Wall(int[][] levelMap, World world) {
        this.world = world;
        int[][] levelMap1 = levelMap;
        wallRows = convertMapToWall(levelMap1);
//        createBody(wallRows);
    }

    private ArrayList<List<Brick>> convertMapToWall(int[][] levelMap) {
        ArrayList<List<Brick>> wall = new ArrayList<List<Brick>>();
        int x = 0;
        int y = 390;
        for (int[] aLevelMap : levelMap) {
            List<Brick> bricks = new ArrayList<Brick>();
            for (int o = 0; o < levelMap[0].length; o++) {

                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set(x + 40, y + 10);
                Brick brick = new Brick(BrickType.byId(aLevelMap[o]), world.createBody(bodyDef));

                bricks.add(brick);
                x += 80;
            }
            wall.add(bricks);
            x = 0;
            y -= 20;
        }
        return wall;
    }

    public void destroy(Brick brick) {
        if (wallRows != null) {

            for (List<Brick> list : wallRows) {
                list.remove(brick);
            }

        }
    }

//    private void createBody(ArrayList<List<Brick>> wallRows) {
//        PolygonShape shape;
//        Fixture brickFixture;
//        for (List<Brick> wallRow : wallRows) {
//            for (Brick brick : wallRow) {
//                shape = new PolygonShape();
//                shape.setAsBox(brick.getTexture().getWidth() / 2,
//                        brick.getTexture().getHeight() / 2,
//                        new Vector2(brick.x() + BRICK_WIDTH / 2,
//                                brick.y() + BRICK_HEIGHT / 2),
//                        0);
//                brickFixture = body.createFixture(shape, 1);
//                brickFixture.setFriction(1);
//                brickFixture.setUserData(EntityDictionary.BRICK);
//                shape.dispose();
//            }
//        }
//    }

    public ArrayList<List<Brick>> getWallArray() {
        return wallRows;
    }

}
