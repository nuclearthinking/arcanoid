package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.nuclearthinking.arcanoid.objects.*;
import com.nuclearthinking.arcanoid.utils.ColorPalette;
import com.nuclearthinking.arcanoid.utils.Vars;

import static com.nuclearthinking.arcanoid.utils.Vars.PPM;

/**
 * Date: 12.03.2016
 * Time: 18:21
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class GameScreen implements Screen {
    private final Arcanoid mainGame;
    private final Resources resources;
    private final Color backgroundColor;
    private OrthographicCamera camera, visual;
    private final Wall wall;
    World world;
    Ball ball;
    Platform platform;
    Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
    Controller controller;
    TopMenu topMenu;

    public GameScreen(final Arcanoid mainGame) {
        this.mainGame = mainGame;
        world = new World(new Vector2(0, 0), true);
        World.setVelocityThreshold(0.5f);
        controller = new Controller(world, mainGame.batch);
        world.setContactListener(new ContactsListener());
        resources = Resources.getInstance();
        backgroundColor = ColorPalette.BACKGROUND;
        topMenu = new TopMenu(mainGame.batch);


        //TODO Создать VIEW для них
        wall = controller.getWall();

        //TODO Перенести в контроллер
        platform = controller.getPlatform();
        ball = controller.getBall();
        visual = new OrthographicCamera();
        camera = new OrthographicCamera();
        visual.setToOrtho(false, Vars.WIDTH, Vars.HEIGHT);
        camera.setToOrtho(false, Vars.WIDTH / PPM, Vars.HEIGHT / PPM);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        controller.update();
        visual.update();
        camera.update();
        mainGame.batch.setProjectionMatrix(visual.combined);

        mainGame.batch.begin();
        //#####START RENDERING###

        topMenu.render();
        wall.render();
        ball.render();
        platform.render();

        //#####END RENDERING#####
        mainGame.batch.end();

        world.step(Gdx.graphics.getDeltaTime(), 8, 3);
        if (Vars.DEBUG) {
            box2DDebugRenderer.render(world, camera.combined);
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
