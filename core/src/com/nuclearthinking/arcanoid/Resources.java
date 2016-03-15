package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.graphics.Color;
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
    private Map<String, Color> colorPalette;

    private Resources() {
        textures = new HashMap<String, Texture>();
        colorPalette = new HashMap<String, Color>();
        fillPalette();
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

    public Color getColor(String key) {
        return colorPalette.get(key);
    }


    void fillPalette() {
        colorPalette.put("brick1", new Color(0xd8e9d8ff));
        colorPalette.put("brick2", new Color(0xfdf3d2ff));
        colorPalette.put("brick3", new Color(0xf3d3d2ff));
        colorPalette.put("brick4", new Color(0xf4d5e4ff));
        colorPalette.put("brick5", new Color(0xdad5e6ff));
        colorPalette.put("brick6", new Color(0xd4e4f2ff));
        colorPalette.put("background", new Color(0xcaccdfff));
    }

}
