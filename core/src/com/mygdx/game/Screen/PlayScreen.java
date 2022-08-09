package com.mygdx.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.Aquamarine;
import com.mygdx.game.Scenes.HUD;
import com.mygdx.game.Tools.BodyBuilder;

import javax.swing.*;

public class PlayScreen implements Screen {

    private Aquamarine game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private HUD hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    public PlayScreen(Aquamarine game){
        this.game = game;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Aquamarine.V_WIDTH/Aquamarine.PPM, Aquamarine.V_HEIGHT/Aquamarine.PPM, gameCam);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
        hud = new HUD(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("test.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1/Aquamarine.PPM);

        world = new World(new Vector2(0, -10 / Aquamarine.PPM), true);
        b2dr = new Box2DDebugRenderer();

        BodyBuilder.buildDynamicBodies(map, world, "1", 0);
        BodyBuilder.buildDynamicBodies(map, world, "2", 0);


    }

    public void handleInput(float dt){
        if (Gdx.input.isTouched()){
            gameCam.position.x += 100 * dt;
        }
    }

    public void update(float dt){
        handleInput(dt);
        gameCam.update();
        mapRenderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        mapRenderer.render();

        b2dr.render(world, gameCam.combined);


        game.batch.setProjectionMatrix(gameCam.combined);
        //game.batch.begin();

        hud.stage.draw();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
