package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.nuclearthinking.arcanoid.utils.ColorPalette;
import com.nuclearthinking.arcanoid.utils.Vars;

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

    private Resources() {
        textures = new HashMap<>();
        textures.put("topmenu", generateTopMenuTexture());
    }

    public static Resources getInstance() {
        return Holder.INSTANCE;
    }

    public void load() {
        loadTexture("arcanoid", "arcanoid.png");
        loadTexture("hearth", "hearth.png");
        loadTexture("playbutton", "playbutton.png");
        loadTexture("banner", "banner.png");
        loadTexture("border", "border.png");
        loadTexture("brick1", "brick1.png");
        loadTexture("brick2", "brick2.png");
        loadTexture("brick3", "brick3.png");
        loadTexture("brick4", "brick4.png");
        loadTexture("brick5", "brick5.png");
        loadTexture("brick6", "brick6.png");
        loadTexture("ball", "ball.png");
    }

    public Texture getTexture(String key) {
        return textures.get(key);
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

    private static class Holder {
        private static final Resources INSTANCE = new Resources();
    }

}
