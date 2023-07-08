package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import java.io.Serializable;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class WarningScreen implements Screen, Serializable {
    final Tank game;
    private Stage stage4;
    private OrthographicCamera camera;
    private Texture img3;
    private FitViewport viewport;
    public WarningScreen(final Tank game){
        this.game = game;
        img3 = new Texture("BlurredGameMap.jpg");
        stage4 = new Stage(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.viewport = new FitViewport(0, 0, camera);
        Gdx.input.setInputProcessor(stage4);
        Skin mySkin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
        Button YesButton = new TextButton("Yes",mySkin);
        YesButton.setSize(150,70);
        YesButton.setPosition(190,200);
        YesButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game));
                dispose();
            }
        });
        Button NoButton = new TextButton("No",mySkin);
        NoButton.setSize(150,70);
        NoButton.setPosition(310,200);
        NoButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PauseScreen(game));
                dispose();
            }
        });
        stage4.addActor(YesButton);
        stage4.addActor(NoButton);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(img3, 0, -80,660,560);
        game.getFont().draw(game.getBatch(), "Are you sure you want to quit??", 215,300);
        game.getBatch().end();
        stage4.act();
        stage4.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        viewport.setWorldSize(width, height);
        stage4.getViewport().update(width, height, true);
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
        img3.dispose();
    }
}
