package com.nuclearthinking.arcanoid;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Arcanoid extends Game {

    SpriteBatch batch;
    private BitmapFont font;

    @Override
    public final void create() {
        Resources.getInstance().load();
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public final void render() {
        super.render();
    }

    @Override
    public final void dispose() {
        batch.dispose();
        font.dispose();
    }
}

