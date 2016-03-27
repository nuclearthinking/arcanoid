package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

/**
 * Date: 27.03.2016
 * Time: 1:55
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class WorldRenderer {

    Box2DDebugRenderer renderer;
    public static float CAMERA_WIDTH = 10f;
    public static float CAMERA_HEIGHT = 15f;
    public float ppuX;
    public float ppuY;


    GameSpace gameSpace;
    public OrthographicCamera camera;

    public WorldRenderer(GameSpace gameSpace, float w, float h, boolean debug) {
        renderer = new Box2DDebugRenderer();
        this.gameSpace = gameSpace;
        CAMERA_WIDTH = w;
        CAMERA_HEIGHT = h;
        ppuX = (float) Gdx.graphics.getWidth() / CAMERA_WIDTH;
        ppuY = (float) Gdx.graphics.getHeight() / CAMERA_HEIGHT;
        this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
    }


    public void setCamera(float x, float y) {
        this.camera.position.set(x, y, 0);
        this.camera.update();
    }

    public void dispose() {
        gameSpace.world.dispose();
    }


}
