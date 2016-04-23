package com.nuclearthinking.arcanoid.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

/**
 * Date: 02.04.2016
 * Time: 12:09
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class DeleteQueue {

    static Array<Body> fixturesToDelete;

    public DeleteQueue() {
        fixturesToDelete = new Array<>();

    }

    public static void add(Body body) {
        fixturesToDelete.add(body);
    }

    public static Array<Body> getQueue() {
        return fixturesToDelete;
    }

    public static void clear() {
        fixturesToDelete.clear();
    }

}
