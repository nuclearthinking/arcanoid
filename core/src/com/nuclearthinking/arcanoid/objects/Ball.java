package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.nuclearthinking.arcanoid.Controller;
import com.nuclearthinking.arcanoid.Resources;
import com.nuclearthinking.arcanoid.utils.EntityDictionary;

import static com.nuclearthinking.arcanoid.utils.Vars.PPM;

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

    static final float RESTITUTION = 1f;
    static final float FRICTION = 1f;
    static final float DENSITY = 10f;
    static final float LINEAR_DAMPING = 0f;
    static final float ANGULAR_DAMPING = 0f;


    public Ball(Controller controller) {
        texture = Resources.getInstance().getTexture("ball");
        world = controller.getWorld();
        spriteBatch = controller.getSpriteBatch();
        prepareBall();
    }

    void prepareBall() {
        body = createBallBody();
        createFixture();
        body.setLinearDamping(LINEAR_DAMPING);
        body.setAngularDamping(ANGULAR_DAMPING);
    }

    Body createBallBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return world.createBody(bodyDef);
    }

    void createFixture() {
        CircleShape circle = new CircleShape();
        circle.setRadius(8f / PPM);
        Fixture ballFixture = body.createFixture(circle, DENSITY);
        ballFixture.setRestitution(RESTITUTION);
        ballFixture.setFriction(FRICTION);
        ballFixture.setUserData(EntityDictionary.BALL);
        circle.dispose();
    }

    public void render() {
        float xPosition = (getPosition().x - (16 / PPM) / 2) * PPM;
        float yPosition = (getPosition().y - (16 / PPM) / 2) * PPM;
        spriteBatch.draw(texture, xPosition, yPosition);
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
