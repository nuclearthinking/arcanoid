package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Date: 12.03.2016
 * Time: 18:21
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class GameScreen implements Screen {
    final Arcanoid mainGame;
    Resources resources;
    Color backgroundColor;
    OrthographicCamera camera;
    Texture topMenu, arcanoid;
    Rectangle arcanoidBody;

    public GameScreen(final Arcanoid mainGame) {
        this.mainGame = mainGame;
        resources = Resources.getInstance();
        backgroundColor = ColorPalette.BACKGROUND;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Vars.WIDTH, Vars.HEIGHT);
        topMenu = resources.getTexture("topmenu");
        arcanoid = resources.getTexture("arcanoid");
        arcanoidBody = resources.getRectangle("arcanoid");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        mainGame.batch.setProjectionMatrix(camera.combined);


        mainGame.batch.begin();
        mainGame.batch.draw(topMenu, 0, Vars.HEIGHT - Vars.TOPMENU_HEIGHT);
        mainGame.batch.draw(arcanoid, arcanoidBody.x, arcanoidBody.y);
        mainGame.batch.end();

        mouseListener();

        if (arcanoidBody.x < 0) {
            arcanoidBody.x = 0;
        }
        if (arcanoidBody.x > 800 - arcanoidBody.width) {
            arcanoidBody.x = 800 - arcanoidBody.width;
        }
    }

    void mouseListener() {
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
        arcanoidBody.x = touchPos.x - arcanoidBody.width / 2;
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
