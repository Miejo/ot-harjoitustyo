package domain;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;


public class GameTest {
    
    public GameTest() {
        
    }
    
    @Test
    public void addingBullets() {
        Game game = new Game();
        game.addBullet();
        ArrayList<Bullet> bullets = game.getBullets();
        assertEquals(1, bullets.size(), 0.01);
    }
    
    @Test
    public void deletingBulletOutOfBounds() {
        Game game = new Game();
        game.addBullet();
        game.updateBullets(4);
        game.destroyBullets();
        ArrayList<Bullet> bullets = game.getBullets();
        assertEquals(0, bullets.size(), 0.01);
    }
    
    @Test
    public void addingEnemies() {
        Game game = new Game();
        game.addEnemy();
        ArrayList<Enemy> enemies = game.getEnemies();
        assertEquals(1, enemies.size(), 0.01);
    }
    
    @Test
    public void deletingEnemyOutOfBounds() {
        Game game = new Game();
        game.addEnemy();
        game.updateEnemies(4);
        game.destroyEnemies();
        ArrayList<Enemy> enemies = game.getEnemies();
        assertEquals(0, enemies.size(), 0.01);
    }
}
