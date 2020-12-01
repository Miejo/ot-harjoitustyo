package domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class BulletTest {
    public BulletTest() {
        
    }
    
    @Test
    public void bulletUpdate() {
        Bullet bullet = new Bullet(100, 100);
        // PositionY in the start is 100
        bullet.update(0.1f);
        // Updates PositionY to 100 - 0.1*600 = 40
        assertEquals(40, bullet.getPositionY(), 0.01);
    }
    
    @Test
    public void bulletCollisionBox() {
        Bullet bullet = new Bullet(100, 100);
        // CollisionBox is a rectangle at 100, 100 with w of 8 and h of 8
        bullet.update(0.1f);
        // Updates collisionbox location to 100, 92
        assertEquals(40, bullet.getCollisionBox().getMinY(), 0.01);
        assertEquals(48, bullet.getCollisionBox().getMaxY(), 0.01);
    }
}
