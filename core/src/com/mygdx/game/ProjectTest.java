package com.mygdx.game;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class ProjectTest {
    @Test
    public void healthTest(){
        Projectile projectile=new Projectile(100f,100f);
        projectile.Update(10);
        float x = projectile.getX();
        assertEquals((int)x,150);
    }


}
