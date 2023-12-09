package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class GameScreen implements Screen, Serializable {
    final Tank game;
    static int flag = 0;
    final TankMovement Tanks;
    private Texture img2;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Stage stage2;
    static TankMovement tankA;
    //private Rectangle dot;
    //private Texture whitedot;
    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private Rectangle rectangle3;
    private Rectangle rectangle4;
    private Texture health1;
    private Texture health2;
    private Texture angle1;
    private Texture angle2;
    private Texture Texture1;
    private Texture Texture2;
    private ArrayList<Projectile> projectiles;
    private ArrayList<ReverseProjectile> reverseProjectiles;
    private String tankName;
    public GameScreen(final Tank game, String tankName, TankMovement tank1){
        this.game = game;
        this.tankName = tankName;
        Tanks = tank1;
        tankA = Tanks;
        Texture1 = new Texture(tankName);
        Texture2 = new Texture("FrostReversed.png");
        rectangle1 = new Rectangle();
        rectangle2 = new Rectangle();
        rectangle3 = new Rectangle();
        rectangle4 = new Rectangle();
        angle1 = new Texture("BlueRectangle.png");
        angle2 = new Texture("BlueRectangle.png");
        health1 = new Texture("OrangeRectangle.png");
        health2 = new Texture("OrangeRectangle.png");
        img2 = new Texture("GameMap.jpeg");
        stage2 = new Stage(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.viewport = new FitViewport(0, 0, camera);
        Gdx.input.setInputProcessor(stage2);
        Skin mySkin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
        ImageButton PauseButton = new ImageButton(mySkin);
        PauseButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("pause.png"))));
        PauseButton.setSize(100,90);
        PauseButton.setPosition(-5,395);
        PauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PauseScreen(game));
                dispose();
            }
        });
        projectiles = new ArrayList<Projectile>();
        reverseProjectiles = new ArrayList<ReverseProjectile>();
//        whitedot = new Texture(Gdx.files.internal("white-circle.png"));
//        dot = new Rectangle();
//        dot.x = 0;
//        dot.y = 0;
//        dot.width = 64;
//        dot.height = 64;
        rectangle1.x = 100;
        rectangle1.y = 420;
        rectangle1.height = 20;
        rectangle1.width = 100;

        rectangle2.x = 480;
        rectangle2.y = 420;
        rectangle2.height = 20;
        rectangle2.width = 100;

        rectangle3.x = 40;
        rectangle3.y = 20;
        rectangle3.width = 10;
        rectangle3.height = 1;
        rectangle4.x = 540;
        rectangle4.y = 20;
        rectangle4.width = 10;
        rectangle4.height = 1;
        stage2.addActor(PauseButton);

    }
    @Override
    public void show() {

    }
    float t = 0f;
    float t2 = 0f;
    int count = 0;

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//            while(dot.x>=0) {
//                t += Gdx.graphics.getDeltaTime();
//                dot.x += projectile.getX(t);
//                dot.y += projectile.getY(t);
//            }
            if (count % 2 == 0) {
                projectiles.add(new Projectile(Tanks.Tank1.getX() + 55, Tanks.Tank1.getY() + 32));

                count++;
            }
        }


        if(Gdx.input.isKeyPressed(Input.Keys.L)){
//            while(dot.x>=0) {
//                t += Gdx.graphics.getDeltaTime();
//                dot.x += projectile.getX(t);
//                dot.y += projectile.getY(t);
//            }
            if (count % 2 != 0) {
                reverseProjectiles.add(new ReverseProjectile(Tanks.Tank2.getX() , Tanks.Tank2.getY() + 32));
                count++;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            Projectile.ReduceAngle(Gdx.graphics.getDeltaTime());
            if(Projectile.getB() > 0 && rectangle3.height>1){
                rectangle3.height -= 100*Gdx.graphics.getDeltaTime();
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            Projectile.IncreaseAngle(Gdx.graphics.getDeltaTime());
            if(Projectile.getA() > 0 && rectangle3.height<50){
                rectangle3.height += 100*Gdx.graphics.getDeltaTime();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            ReverseProjectile.ReduceAngle(Gdx.graphics.getDeltaTime());
            if(ReverseProjectile.getB() > 0 && rectangle4.height>1){
                rectangle4.height -= 100*Gdx.graphics.getDeltaTime();
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            ReverseProjectile.IncreaseAngle(Gdx.graphics.getDeltaTime());
            if(ReverseProjectile.getA() > 0 && rectangle4.height<50){
                rectangle4.height += 100*Gdx.graphics.getDeltaTime();
            }
        }

        //health

        if(!projectiles.isEmpty() && projectiles.get(0).getX() < (Tanks.Tank2.getX()+Tanks.Tank2.width) && projectiles.get(0).getY() < (Tanks.Tank2.getY()+Tanks.Tank2.height)){
            if(Tanks.Tank2.getX() < projectiles.get(0).getX() && Tanks.Tank2.getY() < projectiles.get(0).getY()){
                rectangle2.width -= 2;
                if(rectangle2.width<=0){
                    String msg1 = "Player1 Wins !!";
                    game.setScreen(new GameOver(game,msg1));
                    dispose();
                }
            }
        }

        if(!reverseProjectiles.isEmpty() && reverseProjectiles.get(0).getX() < (Tanks.Tank1.getX()+Tanks.Tank1.width) && reverseProjectiles.get(0).getY() < (Tanks.Tank1.getY()+Tanks.Tank1.height)){
            if(Tanks.Tank1.getX() < reverseProjectiles.get(0).getX() && Tanks.Tank1.getY() < reverseProjectiles.get(0).getY()){
                rectangle1.width -= 2;
                if(rectangle1.width<=0) {
                    String msg2 = "Player2 Wins !!";
                    game.setScreen(new GameOver(game,msg2));
                    dispose();
                }
            }
        }

        ArrayList<Projectile> projectilesToRemove = new ArrayList<Projectile>();
        ArrayList<ReverseProjectile> reverseProjectilesToRemove = new ArrayList<ReverseProjectile>();



        for(Projectile projectile : projectiles){
            t+=(Gdx.graphics.getDeltaTime());
            projectile.Update(t);
            if(projectile.remove){
                projectilesToRemove.add(projectile);
                t=0f;
            }
        }
        projectiles.removeAll(projectilesToRemove);


        for(ReverseProjectile reverseProjectile : reverseProjectiles){
            t2+=(Gdx.graphics.getDeltaTime());
            reverseProjectile.Update(t2);
            if(reverseProjectile.remove){
                reverseProjectilesToRemove.add(reverseProjectile);
                t2=0f;
            }
        }
        reverseProjectiles.removeAll(reverseProjectilesToRemove);


//        Iterator<Projectile> projectileIterator = projectiles.iterator();
//        Iterator<ReverseProjectile> reverseProjectileIterator = reverseProjectiles.iterator();
//
//
//        while(projectileIterator.hasNext()){
//            t+=(Gdx.graphics.getDeltaTime());
//            projectileIterator.next().Update(t);
//            if(projectileIterator.next().remove){
//                projectilesToRemove.add(projectileIterator.next());
//                t=0f;
//            }
//        }
//        projectiles.removeAll(projectilesToRemove);
//
//
//        while(reverseProjectileIterator.hasNext()){
//            t+=(Gdx.graphics.getDeltaTime());
//            reverseProjectileIterator.next().Update(t);
//            if(reverseProjectileIterator.next().remove){
//                reverseProjectilesToRemove.add(reverseProjectileIterator.next());
//                t=0f;
//            }
//        }
//        reverseProjectiles.removeAll(reverseProjectilesToRemove);


        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            Tanks.Tank1.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            Tanks.Tank1.x += 200 * Gdx.graphics.getDeltaTime();
        }
        if (Tanks.Tank1.x < 0)
            Tanks.Tank1.x = 0;
        if (Tanks.Tank1.y < 0)
            Tanks.Tank1.y = 0;
        if (Tanks.Tank1.x > 580)
            Tanks.Tank1.x = 580;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            Tanks.Tank2.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input .Keys.RIGHT))
            Tanks.Tank2.x += 200 * Gdx.graphics.getDeltaTime();

        if (Tanks.Tank2.x < 0)
            Tanks.Tank2.x = 0;
        if (Tanks.Tank2.x > 580)
            Tanks.Tank2.x = 580;
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(img2, 0, -80,660,560);
        game.getBatch().draw(Texture1, Tanks.Tank1.x, Tanks.Tank1.y, Tanks.Tank1.width, Tanks.Tank1.height);
        game.getBatch().draw(Texture2, Tanks.Tank2.x, Tanks.Tank2.y, Tanks.Tank2.width, Tanks.Tank2.height);
        game.getBatch().draw(health1, rectangle1.x, rectangle1.y, rectangle1.width, rectangle1.height);
        game.getBatch().draw(health2, rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
        game.getBatch().draw(angle1, rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height);
        game.getBatch().draw(angle2, rectangle4.x, rectangle4.y, rectangle4.width, rectangle4.height);
        //game.getBatch().draw(whitedot, dot.x, dot.y, dot.width, dot.height);
        for(Projectile projectile : projectiles){
            projectile.render(game.getBatch());
        }
        for(ReverseProjectile reverseProjectile : reverseProjectiles){
            reverseProjectile.render(game.getBatch());
        }
        game.getBatch().end();
        stage2.act();
        stage2.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        viewport.setWorldSize(width, height);
        stage2.getViewport().update(width, height, true);
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
        img2.dispose();
    }
}
