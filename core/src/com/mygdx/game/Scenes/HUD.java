package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Aquamarine;

public class HUD {
    public Stage stage;
    private final Viewport viewport;

    private final Integer timer;

    Label timeLabel;

    public HUD(SpriteBatch sb){
        timer = 0;
        viewport = new FitViewport(Aquamarine.V_WIDTH, Aquamarine.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timeLabel =  new Label("Bruh", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(timeLabel);
        stage.addActor(table);
    }
}
