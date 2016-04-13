package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.nuclearthinking.arcanoid.objects.*;

import static com.nuclearthinking.arcanoid.Vars.PPM;

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
        ball.getBody().setLinearDamping(0);
        ball.getBody().setAngularDamping(0);
        platform = new Platform(world.createBody(kinematicBody()));
        border = new Border(world.createBody(staticBody()));
        wall = new Wall(Level.LEVEL2, world);
    }

    public final void update() {
        mouseListener();
        keyBoardListener();
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        isDead();
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
                ball.getBody().applyLinearImpulse(0f, 0.3f, ball.getPosition().x, ball.getPosition().y, true);
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            System.out.println("F pressed");
            System.out.println("Mass = " + ball.getBody().getMass());
            System.out.println("Velocity = " + ball.getBody().getLinearVelocity());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            float speed = platform.getBody().getLinearVelocity().x;
            speed -= 5;
            platform.getBody().setLinearVelocity(speed, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            float speed = platform.getBody().getLinearVelocity().x;
            speed += 5;
            platform.getBody().setLinearVelocity(speed, 0);
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

    private BodyDef kinematicBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        return bodyDef;
    }
}
