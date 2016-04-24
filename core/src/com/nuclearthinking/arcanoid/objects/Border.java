package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.nuclearthinking.arcanoid.utils.EntityDictionary;
import com.nuclearthinking.arcanoid.utils.Vars;

import static com.nuclearthinking.arcanoid.utils.Vars.PPM;

/**
 * Date: 31.03.2016
 * Time: 21:53
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Border {

    static final float FRICTION = 1f;
    static final float DENSITY = 0f;

    public Border(final Body body) {
        createCeiling(body);
        createLeftBorder(body);
        createRightBorder(body);
    }

    public void createCeiling(Body body) {
        float hx = (Vars.WIDTH / 2) / PPM;
        float hy = (Vars.TOPMENU_HEIGHT / 2) / PPM;
        float x = (Vars.WIDTH / 2) / PPM;
        float y = (Vars.HEIGHT - (Vars.TOPMENU_HEIGHT / 2)) / PPM;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(hx, hy, new Vector2(x, y), 0);
        Fixture ceiling = body.createFixture(shape, DENSITY);
        ceiling.setFriction(FRICTION);
        ceiling.setUserData(EntityDictionary.BORDER);
        shape.dispose();
    }

    public void createLeftBorder(Body body) {
        float hx = (1 / 2) / PPM;
        float hy = (Vars.HEIGHT / 2) / PPM;
        float y = (Vars.HEIGHT / 2) / PPM;
        float x = 0f;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(hx, hy, new Vector2(x, y), 0);
        Fixture leftBorder = body.createFixture(shape, DENSITY);
        leftBorder.setFriction(FRICTION);
        leftBorder.setUserData(EntityDictionary.BORDER);
        shape.dispose();
    }

    void createRightBorder(Body body) {
        float hx = (1 / 2) / PPM;
        float hy = (Vars.HEIGHT / 2) / PPM;
        float x = Vars.WIDTH / PPM;
        float y = (Vars.HEIGHT / 2) / PPM;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(hx, hy, new Vector2(x, y), 0);
        Fixture rightBorder = body.createFixture(shape, DENSITY);
        rightBorder.setFriction(FRICTION);
        rightBorder.setUserData(EntityDictionary.BORDER);
        shape.dispose();
    }

}
