package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOver implements Screen {
    final Tank game;
    private Texture backgroundImage;
    private String msg;
    public GameOver(final Tank game, String msg){
        this.game = game;
        this.msg = msg;
        backgroundImage = new Texture(Gdx.files.internal("TankMenuBackground.jpeg"));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isTouched()){
            game.setScreen(new MainMenu(game));
            dispose();
        }
        ScreenUtils.clear(0, 0, 0, 0);
        game.getBatch().begin();
        game.getBatch().draw(backgroundImage, 0, 0, 640, 480);
        game.getFont().draw(game.getBatch(), msg, 250,300);
        game.getFont().draw(game.getBatch(), "Game Over", 250,250);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

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
