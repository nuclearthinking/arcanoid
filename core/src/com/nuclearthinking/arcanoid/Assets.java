package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 14.03.2016
 * Time: 22:29
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Assets {

    private Assets() {
        textures = new HashMap<String, Texture>();
    }

    public static Assets getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Assets INSTANCE = new Assets();
    }


    private Map<String, Texture> textures;

    public Texture getTexture(String key) {
        return textures.get(key);
    }

    public void loadTexture(String key, String path) {
        textures.put(key, new Texture(path));
    }

}
