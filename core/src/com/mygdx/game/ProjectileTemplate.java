package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class ProjectileTemplate {
    float x;
    float y;
    Texture bomb;

    public boolean remove = false;
    public float gravity = -9.8f;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public Vector2 startPoint = new Vector2(0,0);

    public abstract void Update(float t);

    public void render(SpriteBatch batch){

        batch.draw(bomb, x, y,10,10);
    }

}
