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
        resources.loadTexture("arcanoid", "arcanoid.png");
        resources.loadTexture("hearth", "hearth.png");
        resources.loadTexture("playbutton", "playbutton.png");
        resources.loadTexture("banner", "banner.png");
        resources.loadTexture("border", "border.png");
        resources.loadTexture("brick1", "brick1.png");
        resources.loadTexture("brick2", "brick2.png");
        resources.loadTexture("brick3", "brick3.png");
        resources.loadTexture("brick4", "brick4.png");
        resources.loadTexture("brick5", "brick5.png");
        resources.loadTexture("brick6", "brick6.png");

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
