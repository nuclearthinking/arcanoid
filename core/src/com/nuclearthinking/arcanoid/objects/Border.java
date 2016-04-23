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

    public Border(final Body body) {
        createCeiling(body);
        createLeftBorder(body);
        createRightBorder(body);
    }

    public void createCeiling(Body body) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((Vars.WIDTH / 2) / PPM, (Vars.TOPMENU_HEIGHT / 2) / PPM, new Vector2((Vars.WIDTH / 2) / PPM, (Vars.HEIGHT - (Vars.TOPMENU_HEIGHT / 2)) / PPM), 0);
        Fixture ceiling = body.createFixture(shape, 0);
        ceiling.setFriction(1f);
        ceiling.setUserData(EntityDictionary.BORDER);
        shape.dispose();
    }

    public void createLeftBorder(Body body) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((1 / 2) / PPM, (Vars.HEIGHT / 2) / PPM, new Vector2(0, (Vars.HEIGHT / 2) / PPM), 0);
        Fixture leftBorder = body.createFixture(shape, 0);
        leftBorder.setFriction(1f);
        leftBorder.setUserData(EntityDictionary.BORDER);
        shape.dispose();
    }

    void createRightBorder(Body body) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((1 / 2) / PPM, (Vars.HEIGHT / 2) / PPM, new Vector2(Vars.WIDTH / PPM, (Vars.HEIGHT / 2) / PPM), 0);
        Fixture rightBorder = body.createFixture(shape, 0);
        rightBorder.setFriction(1f);
        rightBorder.setUserData(EntityDictionary.BORDER);
        shape.dispose();
    }

}
