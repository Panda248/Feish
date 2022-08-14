package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Tools.BodyBuilder;

import static com.mygdx.game.Tools.BodyBuilder.getShapeFromRectangle;
import static com.mygdx.game.Tools.BodyBuilder.getTransformedCenterForRectangle;

public class Player extends Sprite {
    private World world;
    private Body b2body;
    private float density;

    public Player(World world, TiledMap tiledMap){
        this.world = world;
        buildPlayer(tiledMap, world, "Player", 0, this);
    }

    private void buildPlayer(TiledMap tiledMap, World world, String layer, int density, Player plr ) {
        MapObject plrObj = tiledMap.getLayers().get(layer).getObjects().    get(0);
        Rectangle rectangle = ((RectangleMapObject)plrObj).getRectangle();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(rectangle.getX() + rectangle.getWidth()/2, rectangle.getY() + rectangle.getHeight()/2);
        b2body = world.createBody(bodyDef);



        //create a fixture for each body from the shape
        Fixture fixture = b2body.createFixture(getShapeFromRectangle(rectangle), density);
        fixture.setFriction(0.1F);

        //setting the position of the body's origin. In this case with zero rotation
        b2body.setTransform(getTransformedCenterForRectangle(rectangle),0);
    }

}
