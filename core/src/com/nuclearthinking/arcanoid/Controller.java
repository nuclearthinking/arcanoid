package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.nuclearthinking.arcanoid.objects.*;
import com.nuclearthinking.arcanoid.utils.DeleteQueue;

import static com.nuclearthinking.arcanoid.utils.Vars.*;

/**
 * Date: 02.04.2016
 * Time: 10:49
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Controller {

    DeleteQueue deleteQueue;
    private World world;
    private Ball ball;
    private Platform platform;
    private Border border;
    private Wall wall;
    private SpriteBatch spriteBatch;
    boolean mouseClicked = false;

    public Controller(World world, SpriteBatch spriteBatch) {
        this.world = world;
        this.spriteBatch = spriteBatch;
        deleteQueue = new DeleteQueue();
        prepareController();
    }

    final void prepareController() {
        world.setContactListener(new ContactsListener());
        ball = new Ball(this);
        platform = new Platform(this);
        border = new Border(world.createBody(staticBody()));
        wall = new Wall(Level.LEVEL2, this);
    }

    public final void update() {
        mouseListener();
        keyBoardListener();
        isDead();
        speedAcceleration();
        executeCleaning();
    }

    private void executeCleaning() {
        for (Body body : DeleteQueue.getQueue()) {
            Brick brick = (Brick) body.getUserData();
            wall.destroy(brick);
            world.destroyBody(body);
            GameState.getInstance().addPoints();
        }
        DeleteQueue.clear();
    }

    public void speedAcceleration() {
        Vector2 speed = ball.getBody().getLinearVelocity();
        float oldSpeed = Math.abs(speed.x) + Math.abs(speed.y);

        float maxSpeed = BALL_MAX_SPEED;
        float minSpeed = BALL_MIN_SPEED;
        if (ball.getBody().isActive()) {
            if (oldSpeed < minSpeed) {
                speed.x = speed.x * 1.05f;
                speed.y = speed.y * 1.05f;
                System.out.println("Speed = " + speed.toString());
                System.out.println("Accelerating");
            }

            if (oldSpeed > maxSpeed) {
                speed.x = speed.x * 0.95f;
                speed.y = speed.y * 0.95f;
                System.out.println("Speed = " + speed.toString());
                System.out.println("Slowing");
            }
            ball.getBody().setLinearVelocity(speed);
        }
    }

    private void mouseListener() {
        Vector2 touchPos = new Vector2();
        touchPos.set((float) Gdx.input.getX() / PPM, (float) Gdx.input.getY() / PPM);
        platform.move(touchPos);
        if (!mouseClicked) {
            ball.move(platform.getPosition());
        }
        if (Gdx.input.justTouched()) {
            if (!mouseClicked) {
                ball.getBody().setActive(true);
                System.out.println("mouse clicked");
                ball.getBody().applyLinearImpulse(0f, 0.6f, ball.getPosition().x, ball.getPosition().y, true);
                mouseClicked = true;

            }
        }
    }

    private void isDead() {
        float ballY = ball.getPosition().y / PPM;
        if (ballY <= 0) {
            System.out.println("Lose one life");
            GameState.getInstance().loseLife();
            mouseClicked = false;
            ball.getBody().setLinearVelocity(0, 0);
            ball.getBody().setActive(false);
        }

    }

    void keyBoardListener() {
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.E)) {
            ball.getBody().setLinearVelocity(0, 0);
            ball.getBody().setActive(false);
            mouseClicked = false;
        }
    }

    private BodyDef kinematicBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        return bodyDef;
    }


    private BodyDef dynamicBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return bodyDef;
    }

    private BodyDef staticBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return bodyDef;
    }


    public Ball getBall() {
        return ball;
    }


    public Platform getPlatform() {
        return platform;
    }

    public Wall getWall() {
        return wall;
    }

    public World getWorld() {
        return world;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
}
