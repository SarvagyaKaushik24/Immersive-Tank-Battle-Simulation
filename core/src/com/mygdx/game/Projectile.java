package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
    //final Tank game;
    private float x;
    private float y;
    private static float a = 5;
    private static float b = 5;

    public static float getA() {
        return a;
    }

    public static float getB() {
        return b;
    }

    private Texture bomb;
    private Texture OrangeRectangle;
    private Rectangle rectangle;
    public boolean remove = false;
    public float gravity = -9.8f;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Vector2 startVelocity = new Vector2(a,b);
    public Vector2 startPoint = new Vector2(0,0);
    public Projectile(float x, float y){
        //this.game = game;
        bomb = new Texture("bomb.png");
        this.x = x;
        this.y = y;
    }
    public static void ReduceAngle(float t){
        if(b>0) {
            Projectile.b -= t * b;
            Projectile.a = (float) Math.pow(50 - (b * b), 0.5);
        }
    }
    public static void IncreaseAngle(float t){
        if(a>0){
            Projectile.b += t * b;
            Projectile.a = (float) Math.pow(50 - (b*b),0.5);
        }

    }

    public void setRectangleDimensions() {
        rectangle.x = 10;
        rectangle.y = 20;
        rectangle.width = 1;
        rectangle.height = 10;
    }


    public void Update(float t){
        x = a * t + x;
        y = 0.5f * gravity * t * t + b * t + y;
        if(this.y<60){
            remove = true;
        }
    }

    public void render(SpriteBatch batch){

        batch.draw(bomb, x, y,10,10);
    }
}
