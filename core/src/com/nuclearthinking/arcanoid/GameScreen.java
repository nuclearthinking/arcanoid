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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.nuclearthinking.arcanoid.objects.*;

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
    Border border;
    Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
    Controller controller;

    public GameScreen(final Arcanoid mainGame) {

        world = new World(new Vector2(0, 0), true);
        controller = new Controller(world);
        world.setContactListener(new ContactsListener());

        this.mainGame = mainGame;
        resources = Resources.getInstance();
        backgroundColor = ColorPalette.BACKGROUND;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Vars.WIDTH, Vars.HEIGHT);

        //TODO Создать VIEW для них
        ballTexsture = resources.getTexture("ball");
        topMenu = resources.getTexture("topmenu");
        arcanoid = resources.getTexture("arcanoid");
        arcanoidBody = resources.getRectangle("arcanoid");
        hearth = resources.getTexture("hearth");
        gameWall = controller.getWall();

        //TODO Перенести в контроллер
        player = new Player();
        platform = controller.getPlatform();
        ball = controller.getBall();
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
        controller.update();

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
                mainGame.batch.draw(brick.getTexture(), brick.getPosition().x - 40, brick.getPosition().y - 10);
            }
        }

        mainGame.batch.end();


        if (arcanoidBody.x < 0) {
            arcanoidBody.x = 0;
        }
        if (arcanoidBody.x > 800 - arcanoidBody.width) {
            arcanoidBody.x = 800 - arcanoidBody.width;
        }

        if (Vars.DEBUG) {
            box2DDebugRenderer.render(world, camera.combined);
        }
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
