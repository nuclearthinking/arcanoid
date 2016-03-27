package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.nuclearthinking.arcanoid.objects.Ball;
import com.nuclearthinking.arcanoid.objects.Brick;
import com.nuclearthinking.arcanoid.objects.Platform;
import com.nuclearthinking.arcanoid.objects.Wall;

import java.util.List;

/**
 * Date: 12.03.2016
 * Time: 18:21
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
class GameScreen implements Screen {
    private final Arcanoid mainGame;
    private final Resources resources;
    private final Color backgroundColor;
    private final OrthographicCamera camera;
    private final Texture topMenu, arcanoid, hearth, ballTexsture;
    private final Rectangle arcanoidBody;
    private final Player player;
    private final Wall gameWall;
    World world;
    Ball ball;
    Platform platform;
    Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();

    public GameScreen(final Arcanoid mainGame) {
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new Contacts());


        this.mainGame = mainGame;
        resources = Resources.getInstance();
        backgroundColor = ColorPalette.BACKGROUND;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Vars.WIDTH, Vars.HEIGHT);
        ballTexsture = resources.getTexture("ball");
        topMenu = resources.getTexture("topmenu");
        arcanoid = resources.getTexture("arcanoid");
        arcanoidBody = resources.getRectangle("arcanoid");
        hearth = resources.getTexture("hearth");

        // ШАР
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        ball = new Ball(world.createBody(bodyDef));
        player = new Player();

        //ПЛАТФОРМА
        BodyDef bodyPlatform = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        platform = new Platform(world.createBody(bodyPlatform));


        //ВЕРХНЕЕ МЕНЮ
        BodyDef ceilingDef = new BodyDef();
        ceilingDef.type = BodyDef.BodyType.StaticBody;
        ceilingDef.position.set(Vars.WIDTH / 2, Vars.HEIGHT - (Vars.TOPMENU_HEIGHT / 2));
        Body ceilingBody = world.createBody(ceilingDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Vars.WIDTH / 2, Vars.TOPMENU_HEIGHT / 2);
        Fixture ceilingFixture = ceilingBody.createFixture(shape, 1);
        shape.dispose();


        //ЛЕВАЯ ГРАНЬ
        BodyDef leftWall = new BodyDef();
        leftWall.type = BodyDef.BodyType.StaticBody;
        leftWall.position.set(1, Vars.HEIGHT / 2);
        Body leftWallBody = world.createBody(leftWall);
        PolygonShape leftWallShape = new PolygonShape();
        shape.setAsBox(1 / 2, Vars.HEIGHT / 2);
        Fixture leftWallFixture = leftWallBody.createFixture(leftWallShape, 1);
        leftWallShape.dispose();

        //ПРАВАЯ ГРАНЬ
        BodyDef rightWall = new BodyDef();
        rightWall.type = BodyDef.BodyType.StaticBody;
        rightWall.position.set(800, Vars.HEIGHT / 2);
        Body rightWallBody = world.createBody(rightWall);
        PolygonShape rightWallShape = new PolygonShape();
        shape.setAsBox(1 / 2, Vars.HEIGHT / 2);
        Fixture rightWallFixture = rightWallBody.createFixture(rightWallShape, 1);
        rightWallShape.dispose();
        gameWall = new Wall(Level.LEVEL2);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        int hearthXPos = Vars.WIDTH - (16 + 5);
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        mainGame.batch.setProjectionMatrix(camera.combined);

        mouseListener();

        mainGame.batch.begin();

        mainGame.batch.draw(ballTexsture, ball.getBody().getPosition().x - 16 / 2, ball.getBody().getPosition().y - 16 / 2);

        mainGame.batch.draw(topMenu, 0, Vars.HEIGHT - Vars.TOPMENU_HEIGHT);

        mainGame.batch.draw(arcanoid, platform.getBody().getPosition().x - Vars.ARCANOID_WIDTH / 2, platform.getBody().getPosition().y - Vars.ARCANOID_HEIGHT / 2);

        for (int i = 0; i < player.getLifeAmount(); i++) {
            mainGame.batch.draw(hearth, hearthXPos, Vars.HEIGHT - 25);
            hearthXPos -= 20;
        }

        for (List<Brick> wallRow : gameWall.getWallArray()) {
            for (Brick brick : wallRow) {
                mainGame.batch.draw(brick.getTexture(), brick.x(), brick.y());
            }
        }

        mainGame.batch.end();


        if (arcanoidBody.x < 0) {
            arcanoidBody.x = 0;
        }
        if (arcanoidBody.x > 800 - arcanoidBody.width) {
            arcanoidBody.x = 800 - arcanoidBody.width;
        }

        box2DDebugRenderer.render(world, camera.combined);
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    private void mouseListener() {
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
        platform.getBody().setTransform(new Vector2(touchPos.x - arcanoidBody.width / 2, 20), 0);
        arcanoidBody.x = touchPos.x - arcanoidBody.width / 2;
        if (Gdx.input.justTouched()) {
            System.out.println("mouse clicked");
            ball.getBody().setLinearVelocity(30, 45);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
