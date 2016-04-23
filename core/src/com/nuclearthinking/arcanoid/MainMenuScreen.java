package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.nuclearthinking.arcanoid.utils.ColorPalette;
import com.nuclearthinking.arcanoid.utils.Vars;

/**
 * Date: 20.03.2016
 * Time: 0:41
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class MainMenuScreen implements Screen {

    private final Arcanoid game;
    private final Resources resources;
    private final OrthographicCamera camera;
    private final Texture banner;
    private final Texture play;
    private final Texture borderBottom;
    private final Texture borderTop;
    private final Rectangle bannerBody;
    private final Rectangle playBody;
    private final Color backgroundColor;
    private final Vector3 clickPoint;

    public MainMenuScreen(final Arcanoid game) {
        this.game = game;

        backgroundColor = ColorPalette.BACKGROUND;
        resources = Resources.getInstance();
        camera = new OrthographicCamera(Vars.WIDTH, Vars.HEIGHT);
        camera.position.set(Vars.WIDTH / 2, Vars.HEIGHT / 2, 0);
        banner = resources.getTexture("banner");
        play = resources.getTexture("playbutton");
        borderBottom = resources.getTexture("border");
        borderTop = resources.getTexture("border");
        bannerBody = new Rectangle();
        bannerBody.width = Vars.BANNER_WIDTH;
        bannerBody.height = Vars.BANNER_HEIGHT;
        bannerBody.x = Vars.WIDTH / 2 - Vars.BANNER_WIDTH / 2;
        bannerBody.y = Vars.HEIGHT - (Vars.HEIGHT / 3);
        playBody = new Rectangle();
        playBody.x = Vars.WIDTH / 2 - Vars.PLAY_BUTTON_WIDTH / 2;
        playBody.y = (float) ((Vars.HEIGHT / 2) / 1.5);
        playBody.width = Vars.PLAY_BUTTON_WIDTH;
        playBody.height = Vars.PLAY_BUTTON_HEIGHT;
        clickPoint = new Vector3();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(banner, bannerBody.x, bannerBody.y);
        game.batch.draw(play, playBody.x, playBody.y);
        game.batch.draw(borderBottom, 0, 0);
        game.batch.draw(borderTop, 0, Vars.HEIGHT - 20);
        game.batch.end();
        if (Gdx.input.justTouched()) {
            camera.unproject(clickPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (playBody.contains(clickPoint.x, clickPoint.y)) {
                game.setScreen(new GameScreen(game));
            }
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
