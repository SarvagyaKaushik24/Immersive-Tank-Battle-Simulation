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

public class PauseScreen implements Screen,Serializable{
    final Tank game;
    private Stage stage3;
    private Texture img3;
    private OrthographicCamera camera;
    private FitViewport viewport;
    public PauseScreen(final Tank game){
        this.game = game;
        img3 = new Texture("BlurredGameMap.jpg");
        stage3 = new Stage(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.viewport = new FitViewport(0, 0, camera);
        Gdx.input.setInputProcessor(stage3);
        Skin mySkin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
        Button ResumeButton = new TextButton("Resume Game",mySkin);
        ResumeButton.setSize(250,70);
        ResumeButton.setPosition(195,300);
        ResumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        Button SaveGameButton = new TextButton("Save Game",mySkin);
        SaveGameButton.setSize(250,70);
        SaveGameButton.setPosition(195,200);
        Button QuitGameButton = new TextButton("Quit Game",mySkin);
        QuitGameButton.setSize(250,70);
        QuitGameButton.setPosition(195,100);
        QuitGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new WarningScreen(game));
                dispose();

            }
        });
        stage3.addActor(ResumeButton);
        stage3.addActor(SaveGameButton);
        stage3.addActor(QuitGameButton);
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
        game.getBatch().end();
        stage3.act();
        stage3.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        viewport.setWorldSize(width, height);
        stage3.getViewport().update(width, height, true);
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
