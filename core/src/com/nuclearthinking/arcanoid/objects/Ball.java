package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.nuclearthinking.arcanoid.Controller;
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
    private Texture texture;
    private SpriteBatch spriteBatch;
    private World world;
    private Body body;

    public Ball(Controller controller) {
        texture = Resources.getInstance().getTexture("ball");
        world = controller.getWorld();
        spriteBatch = controller.getSpriteBatch();
        prepareBall();
    }

    void prepareBall() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(8 / PPM);
        Fixture ballPhysicFixture = body.createFixture(circle, 10f);
        ballPhysicFixture.setRestitution(1f);
        ballPhysicFixture.setFriction(1f);
        ballPhysicFixture.setUserData(EntityDictionary.BALL);
        body.setLinearDamping(0);
        body.setAngularDamping(0);
        circle.dispose();
    }

    public void render() {
        spriteBatch.draw(texture, (getPosition().x - (16 / PPM) / 2) * PPM, (getPosition().y - (16 / PPM) / 2) * PPM);
    }

    public void move(Vector2 vector2) {
        body.setTransform(new Vector2(vector2.x, 38 / PPM), 0);
    }

    public Body getBody() {
        return body;
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }


}
