package com.nuclearthinking.arcanoid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nuclearthinking.arcanoid.Arcanoid;
import com.nuclearthinking.arcanoid.Vars;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Vars.WIDTH;
        config.height = Vars.HEIGHT;
        config.title = Vars.TITLE;
        new LwjglApplication(new Arcanoid(), config);
    }
}
