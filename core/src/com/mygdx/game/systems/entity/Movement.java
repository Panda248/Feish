package com.mygdx.game.systems.entity;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.mygdx.game.Components;

public class Movement extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public Movement() {}

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(Components.position.class, Components.velocity.class).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);
            Components.position position = entity.getComponent(Components.position.class);
            Components.velocity velocity = entity.getComponent(Components.velocity.class);

            position.xPos += velocity.xVel * deltaTime;
            position.yPos += velocity.yVel * deltaTime;
        }
    }
}
