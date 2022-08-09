package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screen.PlayScreen;


public class Aquamarine extends Game {

	public static final float V_WIDTH = 1920;
	public static final float V_HEIGHT = 1080;
	public static final float PPM = 32;
	public static final int TILE_SIZE = 32;
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
