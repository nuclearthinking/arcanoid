package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.nuclearthinking.arcanoid.Resources;
import com.nuclearthinking.arcanoid.Vars;

/**
 * Date: 26.03.2016
 * Time: 22:45
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Platform {

    Texture texture;
    Body body;

    public Platform(Body body) {
        this.body = body;
        texture = Resources.getInstance().getTexture("arcanoid");

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Vars.ARCANOID_WIDTH / 2, Vars.ARCANOID_HEIGHT / 2);
        Fixture fixture = body.createFixture(shape, 1);
        fixture.setFriction(1);
        shape.dispose();
        body.setUserData(this);
    }

    public Body getBody() {
        return body;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public void move(Vector2 vector2) {
        int y = 20;
        if (vector2.x > Vars.ARCANOID_WIDTH / 2 && vector2.x < Vars.WIDTH - Vars.ARCANOID_WIDTH / 2) {
            body.setTransform(vector2.x, y, 0);
        } else {
            if (vector2.x < Vars.ARCANOID_WIDTH / 2) {
                body.setTransform(new Vector2(Vars.ARCANOID_WIDTH / 2, y), 0);
            } else {
                if (vector2.x > Vars.WIDTH - Vars.ARCANOID_WIDTH / 2) {
                    body.setTransform(new Vector2(Vars.WIDTH - Vars.ARCANOID_WIDTH / 2, y), 0);
                }
            }
        }
    }
}
