package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Date: 27.03.2016
 * Time: 19:21
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Contacts implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        System.out.println("contact with somth");
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
