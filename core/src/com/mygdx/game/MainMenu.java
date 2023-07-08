package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import java.io.Serializable;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MainMenu implements Screen, Serializable {
    final Tank game;
    private Texture backgroundImage;

    private OrthographicCamera camera;
    private FitViewport viewport;
    private Stage stage;
    public MainMenu(final Tank game) {
        this.game = game;
        backgroundImage = new Texture(Gdx.files.internal("tank.jpg"));
        //backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1920, 1229);
        stage = new Stage(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.viewport = new FitViewport(0, 0, camera);
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));

        Label title = new Label("Tank Stars",mySkin);
        title.setSize(Gdx.graphics.getWidth(),20);
        title.setPosition(245,400);


        Button LoginButton = new TextButton("Login",mySkin);
        LoginButton.setSize(250,70);
        LoginButton.setPosition(195,320);
        LoginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //ScreenUtils.clear(0, 0, 0, 0);
                game.setScreen(new LoginMenu(game));
                dispose();
            }
        });
        Button button1 = new TextButton("New Game",mySkin);
        button1.setSize(250,70);
        button1.setPosition(195,250);
        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //ScreenUtils.clear(0, 0, 0, 0);
                game.setScreen(new TankMenu(game));
                dispose();
            }
        });

        Button button2 = new TextButton("Resume Game",mySkin);
        button2.setSize(250,70);
        button2.setPosition(195,180);
        Button button3 = new TextButton("Exit Game",mySkin);
        button3.setSize(250,70);
        button3.setPosition(195,110);
        button3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                System.exit(-1);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(title);
        stage.addActor(LoginButton);
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
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
        game.getBatch().draw(backgroundImage, 0, 60, 640, 360);
        game.getBatch().end();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        viewport.setWorldSize(width, height);
        stage.getViewport().update(width, height, true);
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
        backgroundImage.dispose();
    }
}
