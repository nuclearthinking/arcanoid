package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.nuclearthinking.arcanoid.objects.*;

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

    boolean mouseClicked = false;

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
        mouseListener();
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        isDead();
        executeCleaning();
    }

    private void executeCleaning() {
        for (Body body : DeleteQueue.getQueue()) {
            Brick brick = (Brick) body.getUserData();
            wall.destroy(brick);
            world.destroyBody(body);
        }
        DeleteQueue.clear();
    }

    private void mouseListener() {
        Vector2 touchPos = new Vector2();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY());
        platform.move(touchPos);
        if (!mouseClicked) {
            ball.move(platform.getPosition());
        }

        if (Gdx.input.justTouched()) {
            if (!mouseClicked) {
                System.out.println("mouse clicked");
                ball.getBody().setLinearVelocity(0, 200);
                mouseClicked = true;
            }
        }
    }

    private void isDead() {
        int ballY = (int) ball.getPosition().y;
        if (ballY <= 0) {
            System.out.println("Lose one life");
            GameState.getInstance().loseLife();
            mouseClicked = false;
        }

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
}
