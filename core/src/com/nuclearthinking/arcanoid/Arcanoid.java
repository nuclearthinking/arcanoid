package com.nuclearthinking.arcanoid;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Arcanoid extends Game {

    SpriteBatch batch;
    BitmapFont font;
    Resources resources;

    @Override
    public void create() {


        resources = Resources.getInstance();
        resources.loadTexture("brick", "brick.png");
        resources.loadTexture("arcanoid", "arcanoid.png");
        resources.loadTexture("heart", "heart.png");
        resources.loadTexture("playbutton", "playbutton.png");
        resources.loadTexture("banner", "banner.png");

        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
