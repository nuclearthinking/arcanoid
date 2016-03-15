package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 14.03.2016
 * Time: 22:29
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Resources {

    private Map<String, Texture> textures;

    private Resources() {
        textures = new HashMap<String, Texture>();
        textures.put("topmenu", generateTopMenuTexture());
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

    public void loadTexture(String key, String path) {
        textures.put(key, new Texture(path));
    }


    Texture generateTopMenuTexture() {
        Pixmap pixmap = new Pixmap(Variables.WIDTH, Variables.TOPMENU_HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(ColorPalette.TOPMENU);
        pixmap.fill();
        return new Texture(pixmap);
    }

}
