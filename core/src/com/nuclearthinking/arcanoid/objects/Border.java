package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.nuclearthinking.arcanoid.EntityDictionary;
import com.nuclearthinking.arcanoid.Vars;

/**
 * Date: 31.03.2016
 * Time: 21:53
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Border {
    private Body body;

    public Border(final Body body) {
        this.body = body;
        PolygonShape shape;

        // верхняя граница
        shape = new PolygonShape();
        shape.setAsBox(Vars.WIDTH / 2, Vars.TOPMENU_HEIGHT / 2, new Vector2(Vars.WIDTH / 2, Vars.HEIGHT - (Vars.TOPMENU_HEIGHT / 2)), 0);
        Fixture ceiling = body.createFixture(shape, 1);
        ceiling.setUserData(EntityDictionary.BORDER);
        ceiling.setFriction(5);
        shape.dispose();

        //левая граница
        shape = new PolygonShape();
        shape.setAsBox(1 / 2, Vars.HEIGHT / 2, new Vector2(1 / 2, Vars.HEIGHT / 2), 0);
        Fixture leftBorder = body.createFixture(shape, 1);
        leftBorder.setUserData(EntityDictionary.BORDER);
        leftBorder.setFriction(5);
        shape.dispose();

        //правая граница
        shape = new PolygonShape();
        shape.setAsBox(1 / 2, Vars.HEIGHT / 2, new Vector2(Vars.WIDTH - 1, Vars.HEIGHT / 2), 0);
        Fixture rightBorder = body.createFixture(shape, 1);
        rightBorder.setUserData(EntityDictionary.BORDER);
        rightBorder.setFriction(5);
        shape.dispose();
    }


    public Body getBody() {
        return body;
    }
}
