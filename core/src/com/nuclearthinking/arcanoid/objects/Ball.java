package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.nuclearthinking.arcanoid.EntityDictionary;
import com.nuclearthinking.arcanoid.Resources;

import static com.nuclearthinking.arcanoid.Vars.PPM;

/**
 * Date: 26.03.2016
 * Time: 22:45
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Ball {
    private Body body;
    private Texture texture;

    public Ball(Body body) {
        this.body = body;
        texture = Resources.getInstance().getTexture("ball");

        CircleShape circle = new CircleShape();
        circle.setRadius(8 / PPM);
        Fixture ballPhysicFixture = body.createFixture(circle, 300);
        ballPhysicFixture.setRestitution(1.05f);
        ballPhysicFixture.setFriction(10f);
        ballPhysicFixture.setUserData(EntityDictionary.BALL);

        circle.dispose();

    }

    public void move(Vector2 vector2) {
        body.setTransform(new Vector2(vector2.x, 38 / PPM), 0);
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


}
