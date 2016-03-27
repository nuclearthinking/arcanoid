package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 14.03.2016
 * Time: 22:29
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Resources {

    private final Map<String, Texture> textures;
    private final Map<String, Rectangle> rectangleMap;

    private Resources() {
        textures = new HashMap<String, Texture>();
        rectangleMap = new HashMap<String, Rectangle>();
        textures.put("topmenu", generateTopMenuTexture());
        rectangleMap.put("arcanoid", generateArcanoidRectangle());
    }

    public static Resources getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Resources INSTANCE = new Resources();
    }

    public Texture getTexture(String key) {
        return textures.get(key);
    }

    public Rectangle getRectangle(String key) {
        return rectangleMap.get(key);
    }

    public void loadTexture(String key, String path) {
        textures.put(key, new Texture(path));
    }

    private Texture generateTopMenuTexture() {
        Pixmap pixmap = new Pixmap(Vars.WIDTH, Vars.TOPMENU_HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(ColorPalette.TOPMENU);
        pixmap.fill();
        return new Texture(pixmap);
    }

    private Rectangle generateArcanoidRectangle() {
        Rectangle arcanoidRectangle = new Rectangle();
        arcanoidRectangle.width = Vars.ARCANOID_WIDTH;
        arcanoidRectangle.height = Vars.ARCANOID_HEIGHT;
        arcanoidRectangle.x = Vars.WIDTH / 2 - Vars.ARCANOID_WIDTH / 2;
        arcanoidRectangle.y = 5;
        return arcanoidRectangle;
    }

}
