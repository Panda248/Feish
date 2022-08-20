package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Aquamarine;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.Scenes.HUD;
import com.mygdx.game.Tools.BodyBuilder;

public class PlayScreen implements Screen {

    private final Aquamarine game;
    private final OrthographicCamera gameCam;
    private final Viewport gamePort;

    private final HUD hud;

    private final TmxMapLoader mapLoader;
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer mapRenderer;

    private final World world;
    private final Box2DDebugRenderer b2dr;

    private int maxSprint = 8, maxWalk = 4;
    private float runAccel = 1f, walkAccel = 0.5f;

    private final Player player;

    public PlayScreen(Aquamarine game){
        this.game = game;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Aquamarine.V_WIDTH/Aquamarine.PPM, Aquamarine.V_HEIGHT/Aquamarine.PPM, gameCam);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
        hud = new HUD(game.batch);


        mapLoader = new TmxMapLoader();
        map = mapLoader.load("test.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1/Aquamarine.PPM);

        world = new World(new Vector2(0, -15), true);
        b2dr = new Box2DDebugRenderer();

        player = new Player(world, map);

        BodyBuilder.buildBody(map, world, "kys");
        BodyBuilder.buildBody(map, world, "2");
    }

    public void update(float dt){
        userInput(dt);

        world.step(1/60f, 6, 2);

        player.update(dt);

        if(player.curState != Player.State.DEAD) {
            gameCam.position.x = player.b2body.getPosition().x + 10;
        }

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

    public void userInput(float delta){
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)){
            player.jump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && player.b2body.getLinearVelocity().x <= maxSprint){
            player.b2body.applyLinearImpulse(new Vector2(runAccel, 0), player.b2body.getWorldCenter(), true);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D) && player.b2body.getLinearVelocity().x <= maxWalk) {
            player.b2body.applyLinearImpulse(new Vector2(walkAccel, 0), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && player.b2body.getLinearVelocity().x >= -maxSprint){
            player.b2body.applyLinearImpulse(new Vector2(-runAccel, 0), player.b2body.getWorldCenter(), true);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A) && player.b2body.getLinearVelocity().x >= -maxWalk){
            player.b2body.applyLinearImpulse(new Vector2(-walkAccel, 0), player.b2body.getWorldCenter(), true);
        }
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
