package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ReverseProjectile {
    private float x;
    private float y;
    private static float a = 5;
    private static float b = 5;
    private Texture bomb;
    public boolean remove = false;
    public float gravity = -9.8f;
    public Vector2 startVelocity = new Vector2(a,b);
    public Vector2 startPoint = new Vector2(0,0);
    public ReverseProjectile(float x, float y){
        //this.game = game;
        if(bomb == null){
            bomb = new Texture("bomb.png");
        }
        this.x = x;
        this.y = y;
    }
    public void Update(float t){
        x = -1 * a * t + x;
        y = 0.5f * gravity * t * t + startVelocity.y * t + y;
        if(this.y<60){
            remove = true;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public static void ReduceAngle(float t){
        if(b>0) {
            ReverseProjectile.b -= t * b;
            ReverseProjectile.a = (float) Math.pow(50 - (b * b), 0.5);
        }
    }

    public static float getA() {
        return a;
    }

    public static float getB() {
        return b;
    }

    public static void IncreaseAngle(float t){
        if(a>0){
            ReverseProjectile.b += t * b;
            ReverseProjectile.a = (float) Math.pow(50 - (b*b),0.5);
        }

    }
    public void render(SpriteBatch batch){

        batch.draw(bomb, x, y,10,10);
    }
}
