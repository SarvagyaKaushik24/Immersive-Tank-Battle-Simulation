package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TankMovement extends Sprite {
    Rectangle Tank1;
    Rectangle Tank2;

    Texture Tank1Image;
    Texture Tank2Image;

    private Vector2 velocity1;
    private float speed;

    public TankMovement(){
        Tank1Image = new Texture(Gdx.files.internal("Abrams.png"));
        Tank2Image = new Texture(Gdx.files.internal("FrostReversed.png"));
        velocity1 = new Vector2();
        speed = 50;
        Tank1 = new Rectangle();
        Tank2 = new Rectangle();

        Tank1.x = 0;
        Tank1.y = 55;
        Tank1.width = 64;
        Tank1.height = 64;

        Tank2.x = 580;
        Tank2.y = 60;
        Tank2.width = 64;
        Tank2.height = 64;
    }

    //@Override
//    public void draw(Rectangle rectangle){
//        update(Gdx.graphics.getDeltaTime());
//        super.draw(rectangle);
//    }
//    public void UpdatePosition(float delta){
//        float angle = (float) Math.atan2(1,1);
//        velocity1.set((float) Math.cos(angle)*speed, (float) Math.sin(angle)*speed);
//        Tank1.setPosition(getX() + velocity1.x*delta,getY() + velocity1.y*delta);
//    }
}
