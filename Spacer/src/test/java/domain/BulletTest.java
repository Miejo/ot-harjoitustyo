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
        bullet.update();
        // Updates PositionY to 100 - 8 = 92
        assertEquals(92, bullet.getPositionY(), 0.01);
    }
    
    @Test
    public void bulletCollisionBox() {
        Bullet bullet = new Bullet(100, 100);
        // CollisionBox is a rectangle at 100, 100 with w of 8 and h of 8
        bullet.update();
        // Updates collisionbox location to 100, 92
        assertEquals(92, bullet.getCollisionBox().getMinY(), 0.01);
        assertEquals(100, bullet.getCollisionBox().getMaxY(), 0.01);
    }
}
