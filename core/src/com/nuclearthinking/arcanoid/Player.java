package com.nuclearthinking.arcanoid;

/**
 * Date: 20.03.2016
 * Time: 16:29
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */

//TODO: Удалить игрока
public class Player {

    private int lifeAmount;


    public Player() {
        lifeAmount = 3;
    }

    public boolean takeLife() {
        lifeAmount = lifeAmount - 1;
        if (lifeAmount <= 0) {
            return false;
        }
        return true;
    }

    public int getLifeAmount() {
        return lifeAmount;
    }
}
