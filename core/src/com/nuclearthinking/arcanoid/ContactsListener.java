package com.nuclearthinking.arcanoid;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.nuclearthinking.arcanoid.objects.Brick;

/**
 * Date: 27.03.2016
 * Time: 19:21
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class ContactsListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {


    }

    @Override
    public void endContact(Contact contact) {
        if (contact.getFixtureA().getBody().getUserData() instanceof Brick && contact.getFixtureB().getUserData() == EntityDictionary.BALL) {
            System.out.println("THIS IS BRICK!");
            DeleteQueue.add(contact.getFixtureA().getBody());
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
