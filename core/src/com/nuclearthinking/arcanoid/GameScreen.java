package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

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

    public GameScreen(final Arcanoid mainGame) {
        this.mainGame = mainGame;
        resources = Resources.getInstance();
        backgroundColor = ColorPalette.BACKGROUND;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Variables.WIDTH, Variables.HEIGHT);
        topMenu = resources.getTexture("topmenu");
        arcanoid = resources.getTexture("arcanoid");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainGame.batch.begin();
        mainGame.batch.draw(topMenu, 0, Variables.HEIGHT - Variables.TOPMENU_HEIGHT);

        mainGame.batch.end();


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
