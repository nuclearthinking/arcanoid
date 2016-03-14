package com.nuclearthinking.arcanoid;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Arcanoid extends Game {

    SpriteBatch batch;
    BitmapFont font;
    private Assets assets;

    @Override
    public void create() {


        assets = Assets.getInstance();
        assets.loadTexture("brick","brick.png");
        assets.loadTexture("arcanoid","arcanoid.png");
        assets.loadTexture("heart", "heart.png");

        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new GameScreen(this));
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
