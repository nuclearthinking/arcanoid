package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Date: 26.03.2016
 * Time: 22:45
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Ball {
    private Fixture ballPhysicFixture;
    private Body body;

    public Ball(Body body) {
        this.body = body;
        CircleShape circle = new CircleShape();
        circle.setRadius(6);
        circle.setPosition(new Vector2(1, 1));
        ballPhysicFixture = body.createFixture(circle, 1);
        circle.dispose();
        body.setBullet(true);
    }

    public Body getBody() {
        return body;
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }


}
