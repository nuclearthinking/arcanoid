package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.nuclearthinking.arcanoid.objects.Ball;
import com.nuclearthinking.arcanoid.objects.Platform;
import com.nuclearthinking.arcanoid.objects.Wall;

/**
 * Date: 26.03.2016
 * Time: 22:27
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class GameSpace {

    public World world;
    GameState gameState;
    Platform platform;
    Ball ball;
    Wall wall;

    public GameSpace() {
        world = new World(new Vector2(0, 0), true);
        BodyDef ballBodyDef = new BodyDef();
        ballBodyDef.type = BodyDef.BodyType.KinematicBody;
        ball = new Ball(world.createBody(ballBodyDef));
        ball.getBody().setTransform(3, 4, 0);
        ball.getBody().setFixedRotation(true);
    }

}
