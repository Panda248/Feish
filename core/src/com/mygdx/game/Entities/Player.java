package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Tools.BodyBuilder;

import static com.mygdx.game.Tools.BodyBuilder.getShapeFromRectangle;
import static com.mygdx.game.Tools.BodyBuilder.getTransformedCenterForRectangle;

public class Player extends Sprite {
    private World world;
    private Body b2body;
    private float density;

    public Player(World world, TiledMap tiledMap){
        this.world = world;
        buildPlayer();
    }

    private void buildPlayer() {



        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(32,32);
        b2body = world.createBody(bodyDef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

}
