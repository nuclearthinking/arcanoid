package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Date: 12.03.2016
 * Time: 18:21
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class GameScreen implements Screen {
    final Arcanoid mainGame;

    public GameScreen(final Arcanoid mainGame) {
        this.mainGame = mainGame;


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float) 202 / 255, (float) 204 / 255, (float) 223 / 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
