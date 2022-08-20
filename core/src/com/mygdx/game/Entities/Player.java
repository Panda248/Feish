package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Player extends Sprite {
    public enum State { FALLING, STANDING, WALKING, SPRINTING, JUMPING, SWIMMING, DEAD}

    public State prevState, curState;

    public World world;
    public Body b2body;

    private final boolean playerDead = false;
    private float stateTimer;

    private float density;



    public Player(World world, TiledMap tiledMap){
        this.world = world;
        stateTimer = 0;
        buildPlayer();
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        //setRegion(getFrame(dt));
        getFrame(dt);
    }

    public State getState(){
        if(playerDead)
            return State.DEAD;
        else if((b2body.getLinearVelocity().y > 0 && curState == State.JUMPING) || (b2body.getLinearVelocity().y < 0 && prevState == State.JUMPING))
            return State.JUMPING;
        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if(b2body.getLinearVelocity().x != 0)
            return State.WALKING;
        else
            return State.STANDING;
    }

    public TextureRegion getFrame(float dt){
        curState = getState();

        TextureRegion region;

        stateTimer = curState == prevState ? stateTimer + dt : 0;
        prevState = curState;
        return null;
    }

    public void jump(){
        if ( curState != State.JUMPING ) {
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            curState = State.JUMPING;
        }
    }

    private void buildPlayer() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(2,2);
        b2body = world.createBody(bodyDef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f);

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    public float getStateTimer(){
        return stateTimer;
    }
}
