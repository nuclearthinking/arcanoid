package com.nuclearthinking.arcanoid.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nuclearthinking.arcanoid.Arcanoid;
import com.nuclearthinking.arcanoid.utils.Vars;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Vars.WIDTH;
        config.height = Vars.HEIGHT;
        config.title = Vars.TITLE;
        config.addIcon("icon.png", Files.FileType.Internal);
        new LwjglApplication(new Arcanoid(), config);
    }
}
