package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.nuclearthinking.arcanoid.utils.BrickType;
import com.nuclearthinking.arcanoid.Resources;

import static com.nuclearthinking.arcanoid.utils.Vars.*;

/**
 * Date: 20.03.2016
 * Time: 21:42
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Brick {


    private final Texture texture;
    private Body body;


    public Brick(final BrickType brickType, Body body) {
        this.body = body;
        texture = textureChooser(brickType);
        createBrickBody();
        body.setUserData(this);
    }

    private void createBrickBody() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((BRICK_WIDTH / 2) / PPM, (BRICK_HEIGHT / 2) / PPM);
        Fixture brickFixture = body.createFixture(shape, 0);
        brickFixture.setFriction(1f);
        shape.dispose();
    }


    private Texture textureChooser(final BrickType brickType) {
        switch (brickType) {
            case BRICK1:
                return Resources.getInstance().getTexture("brick1");
            case BRICK2:
                return Resources.getInstance().getTexture("brick2");
            case BRICK3:
                return Resources.getInstance().getTexture("brick3");
            case BRICK4:
                return Resources.getInstance().getTexture("brick4");
            case BRICK5:
                return Resources.getInstance().getTexture("brick5");
            case BRICK6:
                return Resources.getInstance().getTexture("brick6");

        }

        return Resources.getInstance().getTexture("brick1");
    }


    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

}
