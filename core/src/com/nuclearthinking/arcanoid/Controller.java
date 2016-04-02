package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.nuclearthinking.arcanoid.objects.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 02.04.2016
 * Time: 10:49
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Controller {

    private DeleteQueue deleteQueue;
    private GameState gameState;
    private World world;
    private Ball ball;
    private Platform platform;
    private Border border;
    private Wall wall;

    public Controller(World world) {
        this.world = world;
        deleteQueue = new DeleteQueue();
        prepareController();
    }

    final void prepareController() {
        world.setContactListener(new ContactsListener());
        ball = new Ball(world.createBody(dynamicBody()));
        platform = new Platform(world.createBody(staticBody()));
        border = new Border(world.createBody(staticBody()));
        wall = new Wall(Level.LEVEL2, world);
    }

    public final void update() {
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        executeCleaning();
    }

    private final BodyDef dynamicBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return bodyDef;
    }

    private final BodyDef staticBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return bodyDef;
    }


    private void executeCleaning() {
        Array<Body> deleteQueue = DeleteQueue.getQueue();
        ArrayList<List<Brick>> bricklist = wall.getWallArray();
        for (Body body : deleteQueue) {
            Brick brick = (Brick) body.getUserData();
            wall.destroy(brick);
            world.destroyBody(body);
        }


        DeleteQueue.clear();
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }
}
