package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.nuclearthinking.arcanoid.Controller;
import com.nuclearthinking.arcanoid.Resources;
import com.nuclearthinking.arcanoid.utils.Vars;

import static com.nuclearthinking.arcanoid.utils.Vars.PPM;

/**
 * Date: 26.03.2016
 * Time: 22:45
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Platform {

    private final Texture texture;
    private Body platformBody;
    private final World world;
    private final SpriteBatch spriteBatch;

    public Platform(Controller controller) {
        texture = Resources.getInstance().getTexture("arcanoid");
        world = controller.getWorld();
        spriteBatch = controller.getSpriteBatch();
        preparePlatform();
    }


    private void preparePlatform() {
        platformBody = createDynamicalBody();
        Body anchorBody = createKinematicBody();
        createPlatformFixture(platformBody);
        createAnchorFixture(anchorBody);
        createJoint(platformBody, anchorBody);
        anchorBody.setTransform(4f, 0.2f, 0);
        platformBody.setTransform(4f, 0.2f, 0);
    }


    public void render() {
        spriteBatch.draw(texture, (getPosition().x - (Vars.ARCANOID_WIDTH / PPM) / 2) * PPM, (getPosition().y - (Vars.ARCANOID_HEIGHT / PPM) / 2) * PPM);
    }

    private Body createKinematicBody() {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.KinematicBody;
        return world.createBody(bDef);
    }

    private Body createDynamicalBody() {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        return world.createBody(bDef);
    }

    private void createPlatformFixture(Body body) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((Vars.ARCANOID_WIDTH / 2) / PPM, (Vars.ARCANOID_HEIGHT / 2) / PPM);
        Fixture fixture = body.createFixture(shape, 0);
        fixture.setFriction(10f);
        shape.dispose();
    }

    private void createAnchorFixture(Body body) {
        CircleShape shape = new CircleShape();
        shape.setRadius(0.02f);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.isSensor = true;
        body.createFixture(fDef);
    }


    private void createJoint(Body platformBody, Body anchorBody) {
        PrismaticJointDef prismaticJointDef = new PrismaticJointDef();
        platformBody.setLinearDamping(5f);
        prismaticJointDef.bodyA = platformBody;
        prismaticJointDef.bodyB = anchorBody;
        prismaticJointDef.collideConnected = false;
        prismaticJointDef.enableLimit = true;
        prismaticJointDef.upperTranslation = 3.37f;
        prismaticJointDef.lowerTranslation = -3.37f;
        world.createJoint(prismaticJointDef);
    }

    public Vector2 getPosition() {
        return platformBody.getPosition();
    }

    public void move(Vector2 vector2) {
        float platformX = getPosition().x;

        if (vector2.x < platformX - 0.05f) {
            platformBody.applyLinearImpulse(new Vector2(-0.6f, 0f), platformBody.getPosition(), true);
        }
        if (vector2.x > platformX + 0.05f) {
            platformBody.applyLinearImpulse(new Vector2(0.6f, 0f), platformBody.getPosition(), true);
        }
        if (vector2.x <= platformX + 0.05f && vector2.x >= platformX - 0.05f) {
            platformBody.setLinearVelocity(0, 0);
        }
    }
}
