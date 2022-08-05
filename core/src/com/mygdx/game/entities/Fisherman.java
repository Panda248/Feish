package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.Components;

public class Fisherman extends Entity {
    public Fisherman(float x, float y)  {
        this.add(new Components.position());
        this.getComponent(Components.position.class).xPos = x;
        this.getComponent(Components.position.class).yPos = y;
    }
}
