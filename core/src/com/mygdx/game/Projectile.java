package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Projectile extends ProjectileTemplate implements Serializable {
    private static float a = 5;
    private static float b = 5;

    public static float getA() {
        return a;
    }

    public static float getB() {
        return b;
    }

    public Vector2 startVelocity = new Vector2(a,b);
    public Projectile(float x, float y){
        //this.game = game;
        try {
            bomb = new Texture("bomb.png");
        }catch(Exception e){}
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

    @Override
    public void Update(float t){
        x = a * t + x;
        y = 0.5f * gravity * t * t + b * t + y;
        if(this.y<60){
            remove = true;
        }
    }

}
