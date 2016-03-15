package com.nuclearthinking.arcanoid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nuclearthinking.arcanoid.Arcanoid;
import com.nuclearthinking.arcanoid.Variables;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Variables.WIDTH;
        config.height = Variables.HEIGHT;
        config.title = Variables.TITLE;
        new LwjglApplication(new Arcanoid(), config);
    }
}
