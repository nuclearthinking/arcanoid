package com.nuclearthinking.arcanoid.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nuclearthinking.arcanoid.FontFactory;
import com.nuclearthinking.arcanoid.GameState;
import com.nuclearthinking.arcanoid.Resources;
import com.nuclearthinking.arcanoid.Vars;

/**
 * Date: 23.04.2016
 * Time: 22:23
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class TopMenu {
    Texture hearth, topMenu;
    SpriteBatch spriteBatch;


    public TopMenu(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
        hearth = Resources.getInstance().getTexture("hearth");
        topMenu = Resources.getInstance().getTexture("topmenu");
    }

    public void render() {
        int hearthXPos = Vars.WIDTH - (16 + 5);
        spriteBatch.draw(topMenu, 0, Vars.HEIGHT - Vars.TOPMENU_HEIGHT);
        for (int i = 0; i < GameState.getInstance().getLifeAmount(); i++) {
            spriteBatch.draw(hearth, hearthXPos, Vars.HEIGHT - 25);
            hearthXPos -= 20;
        }
        FontFactory.getFont9().draw(spriteBatch, GameState.getInstance().getScoreString(), 20, 445);
    }
}
