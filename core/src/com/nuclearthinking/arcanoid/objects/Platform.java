package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.nuclearthinking.arcanoid.Vars;

/**
 * Date: 26.03.2016
 * Time: 22:45
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Platform {

    Fixture fixture;
    Body platformBody;

    public Platform(Body body) {
        this.platformBody = body;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Vars.ARCANOID_WIDTH / 2, Vars.ARCANOID_HEIGHT / 2);
        fixture = body.createFixture(shape, 1);
        fixture.setFriction(1);
        fixture.setUserData("platform");
        shape.dispose();
    }

    public Body getBody() {
        return platformBody;
    }
}
