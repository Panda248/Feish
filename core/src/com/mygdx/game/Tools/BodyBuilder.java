package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Aquamarine;
import com.mygdx.game.Entities.Player;

public class BodyBuilder {

    public enum BodyType{STATIC, DYNAMIC, KINEMATIC}



    public static void buildDynamicBodies(TiledMap tiledMap, World world, String layer, int density){
        MapObjects objects = tiledMap.getLayers().get(layer).getObjects();
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        for (MapObject object: objects) {
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();

            //create a dynamic within the world body (also can be KinematicBody or StaticBody
            //bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / Aquamarine.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / Aquamarine.PPM);
            Body body = world.createBody(bodyDef);

            //create a fixture for each body from the shape
            Fixture fixture = body.createFixture(getShapeFromRectangle(rectangle), density);
            fixture.setFriction(0.1F);

            shape.setAsBox(rectangle.getWidth() / 2 / Aquamarine.PPM, rectangle.getHeight() / 2 / Aquamarine.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);

            //setting the position of the body's origin. In this case with zero rotation
            //body.setTransform(getTransformedCenterForRectangle(rectangle),0);
        }
    }

    public static Shape getShapeFromRectangle(Rectangle rectangle){
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(rectangle.width*0.5F/ Aquamarine.TILE_SIZE,rectangle.height*0.5F/ Aquamarine.TILE_SIZE);
        return polygonShape;
    }
    public static Vector2 getTransformedCenterForRectangle(Rectangle rectangle){
        Vector2 center = new Vector2();
        rectangle.getCenter(center);
        return center.scl(1/Aquamarine.TILE_SIZE);
    }
}
