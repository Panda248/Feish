package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.systems.entity.Movement;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	World world;

	Family physFam;

	Movement moveSys;

	Engine entEngine;




	@Override
	public void create () {
		world = new World(new Vector2(0,-10), true);
		//physFam = Family.all(Components.physics.class).get();dont mattter until physics is added

		moveSys = new Movement();

		entEngine = new Engine();
		//entEngine.addEntityListener(physFam, );
		entEngine.addSystem(moveSys);

		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		entEngine.update(Gdx.graphics.getDeltaTime());
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
