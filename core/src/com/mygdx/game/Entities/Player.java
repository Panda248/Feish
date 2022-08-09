package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Tools.BodyBuilder;

public class Player extends Sprite {
    private World world;
    private Body b2body;
    private int x;
    private int y;
    private float density;

    public Player(World world){
        this.world = world;
        //BodyBuilder.buildPlayer();
    }

}
