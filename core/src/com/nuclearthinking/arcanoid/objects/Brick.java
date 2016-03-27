package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.nuclearthinking.arcanoid.BrickType;
import com.nuclearthinking.arcanoid.Resources;

/**
 * Date: 20.03.2016
 * Time: 21:42
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Brick {


    private static final int BRICK_WIDTH = 80;
    private static final int BRICK_HEIGHT = 20;

    private final Resources resources;
    private final Texture texture;
    private final Rectangle body;


    public Brick(final int x, final int y, final BrickType brickType) {
        resources = Resources.getInstance();
        body = new Rectangle();
        body.width = BRICK_WIDTH;
        body.height = BRICK_HEIGHT;
        body.x = x;
        body.y = y;
        texture = textureChooser(brickType);
    }


    private Texture textureChooser(final BrickType brickType) {
        switch (brickType) {
            case BRICK1:
                return resources.getTexture("brick1");
            case BRICK2:
                return resources.getTexture("brick2");
            case BRICK3:
                return resources.getTexture("brick3");
            case BRICK4:
                return resources.getTexture("brick4");
            case BRICK5:
                return resources.getTexture("brick5");
            case BRICK6:
                return resources.getTexture("brick6");

        }

        return resources.getTexture("brick1");
    }


    public Texture getTexture() {
        return texture;
    }

    public float x() {
        return body.x;
    }

    public float y() {
        return body.y;
    }

}
