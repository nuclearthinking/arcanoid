package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Date: 02.04.2016
 * Time: 21:02
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class FontFactory {
    private static FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.TTF"));
    private static BitmapFont font10;
    private static BitmapFont font25;

    public static BitmapFont getFont10() {
        if (font10 == null) {
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 10;
            generateFont8(parameter);
        }
        return font10;
    }

    public static BitmapFont getFont25() {
        if (font25 == null) {
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 25;
            generateFont25(parameter);
        }
        return font25;
    }

    private static void generateFont8(FreeTypeFontGenerator.FreeTypeFontParameter param) {
        font10 = generator.generateFont(param);
    }

    private static void generateFont25(FreeTypeFontGenerator.FreeTypeFontParameter param) {
        font25 = generator.generateFont(param);
    }
}
