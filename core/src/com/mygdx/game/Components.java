package com.mygdx.game;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class Components {
    public static class position implements Component  {
        ComponentMapper<position> posMap = ComponentMapper.getFor(position.class);
        public float xPos = 0;
        public float yPos = 0;
    }
    public static class velocity implements Component  {
        ComponentMapper<velocity> velMap = ComponentMapper.getFor(velocity.class);
        public float xVel = 0;
        public float yVel = 0;
    }
    public static class health implements Component {
        ComponentMapper<health> helMap = ComponentMapper.getFor(health.class);
        public int health = 1;
    }
    public static class physics implements Component   {
        ComponentMapper<physics> physMap = ComponentMapper.getFor(physics.class);
        //physics idk
    }
}
