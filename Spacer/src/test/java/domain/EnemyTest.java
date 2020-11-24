package domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class EnemyTest {
    
    public EnemyTest() {
    }
    
    @Test
    public void bulletConstructor() {
        Enemy enemy = new Enemy();
        // PositionY in the start is 10
        // PositionX should be between 0 and 736, so test with 368 +- 368
        assertEquals(368, enemy.getPositionX(), 368);
    }
    
    @Test
    public void enemyCollisionBox() {
        Enemy enemy = new Enemy();
        // CollisionBox is a rectangle at x, 10 with w of 64 and h of 64
        double testPosX = enemy.getPositionX();
        enemy.update();
        // Updates collisionbox location to x, 10 + 2
        assertEquals(testPosX, enemy.getCollisionBox().getMinX(), 0.01);
        assertEquals(76, enemy.getCollisionBox().getMaxY(), 0.01);
    }
}
