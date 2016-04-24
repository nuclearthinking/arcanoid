package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.nuclearthinking.arcanoid.Controller;
import com.nuclearthinking.arcanoid.utils.BrickType;

import java.util.ArrayList;
import java.util.List;

import static com.nuclearthinking.arcanoid.utils.Vars.PPM;

/**
 * Date: 20.03.2016
 * Time: 21:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Wall {

    private final ArrayList<List<Brick>> wallRows;
    World world;
    SpriteBatch spriteBatch;

    public Wall(int[][] levelMap, Controller controller) {
        this.world = controller.getWorld();
        this.spriteBatch = controller.getSpriteBatch();
        wallRows = convertMapToWall(levelMap);
    }

    private ArrayList<List<Brick>> convertMapToWall(int[][] levelMap) {
        ArrayList<List<Brick>> wall = new ArrayList<>();
        int x = 0;
        int y = 390;
        for (int[] aLevelMap : levelMap) {
            List<Brick> bricks = new ArrayList<>();
            for (int o = 0; o < levelMap[0].length; o++) {
                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((x + 40) / PPM, (y + 10) / PPM);
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

    public void render() {
        for (List<Brick> wallRow : wallRows) {
            for (Brick brick : wallRow) {
                float xPosition = (brick.getPosition().x - 40f / PPM) * PPM;
                float yPosition = (brick.getPosition().y - 10f / PPM) * PPM;
                spriteBatch.draw(brick.getTexture(), xPosition, yPosition);
            }
        }
    }
}
