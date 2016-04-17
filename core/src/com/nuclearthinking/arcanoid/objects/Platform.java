package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.nuclearthinking.arcanoid.Resources;
import com.nuclearthinking.arcanoid.Vars;

import static com.nuclearthinking.arcanoid.Vars.PPM;

/**
 * Date: 26.03.2016
 * Time: 22:45
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Platform {

    Texture texture;
    Body platformBody, anchorBody;
    World world;
    PrismaticJoint joint;

    public Platform(World world) {
        this.texture = Resources.getInstance().getTexture("arcanoid");
        this.world = world;
        platformBody = createDynamicalBody();
        createPlatformFixture(platformBody);

        anchorBody = createKinematicBody();
        createAnchorFixture(anchorBody);
        createJoint(platformBody, anchorBody);
        anchorBody.setTransform(4f, 0.2f, 0);
        platformBody.setTransform(4f, 0.2f, 0);
    }

    Body createKinematicBody() {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.KinematicBody;
        return world.createBody(bDef);
    }

    Body createDynamicalBody() {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        return world.createBody(bDef);
    }

    void createPlatformFixture(Body body) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((Vars.ARCANOID_WIDTH / 2) / PPM, (Vars.ARCANOID_HEIGHT / 2) / PPM);
        Fixture fixture = body.createFixture(shape, 0);
        fixture.setFriction(1f);
        shape.dispose();
    }

    void createAnchorFixture(Body body) {
        CircleShape shape = new CircleShape();
        shape.setRadius(0.02f);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.isSensor = true;
        body.createFixture(fDef);
    }


    public PrismaticJoint getJoint() {
        return joint;
    }

    void createJoint(Body platformBody, Body anchorBody) {
        PrismaticJointDef prismaticJointDef = new PrismaticJointDef();
        platformBody.setLinearDamping(5f);
        prismaticJointDef.bodyA = platformBody;
        prismaticJointDef.bodyB = anchorBody;
        prismaticJointDef.collideConnected = false;
        prismaticJointDef.enableLimit = true;
        prismaticJointDef.upperTranslation = 3.37f;
        prismaticJointDef.lowerTranslation = -3.37f;

        joint = (PrismaticJoint) world.createJoint(prismaticJointDef);
    }

    public Body getPlatformBody() {
        return platformBody;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return platformBody.getPosition();
    }

    public void move(Vector2 vector2) {
        float platformX = getPosition().x;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            System.out.println("Позиция платформы = " + platformX);
            System.out.println("Ппозиция курсора = " + vector2.x);
            System.out.println("Разность между позициями = " + (platformX - vector2.x));
        }

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
