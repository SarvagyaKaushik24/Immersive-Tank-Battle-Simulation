package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.Serializable;

public class TankMenu implements Screen, Serializable {
    final Tank game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Stage Tankstage;
    private Texture backgroundImage;
    public TankMenu(final Tank game){
        this.game = game;
        backgroundImage = new Texture(Gdx.files.internal("TankMenuBackground.jpeg"));
        Tankstage = new Stage(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.viewport = new FitViewport(0, 0, camera);

        Gdx.input.setInputProcessor(Tankstage);
        Skin mySkin1 = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
        Skin mySkin2 = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
        Skin mySkin3 = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
        ImageButton BuratinoButton = new ImageButton(mySkin3);

        BuratinoButton.setSize(200,180);
        BuratinoButton.setPosition(420,200);
        BuratinoButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        ImageButton FrostButton = new ImageButton(mySkin2);

        FrostButton.setSize(200,180);
        FrostButton.setPosition(220,200);
        FrostButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        ImageButton AbramsButton = new ImageButton(mySkin1);

        AbramsButton.setSize(200,180);
        AbramsButton.setPosition(20,200);
        AbramsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        AbramsButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Abrams.png"))));
        AbramsButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Abrams.png"))));
        Tankstage.addActor(AbramsButton);
        FrostButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Frost.png"))));
        FrostButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Frost.png"))));
        Tankstage.addActor(FrostButton);
        BuratinoButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Buratino.png"))));
        BuratinoButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Buratino.png"))));
        Tankstage.addActor(BuratinoButton);

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
        game.getBatch().draw(backgroundImage, 0, 0, 640, 480);
        game.getFont().draw(game.getBatch(), "Abrams", 90,200);
        game.getFont().draw(game.getBatch(), "Frost", 300,200);
        game.getFont().draw(game.getBatch(), "Buratino", 490,200);
        game.getBatch().end();
        Tankstage.act();
        Tankstage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        viewport.setWorldSize(width, height);
        Tankstage.getViewport().update(width, height, true);
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
