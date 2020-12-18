package domain;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class GameTest {
    
    Game game;
    ArrayList<String> input;
    
    @Before
    public void setUp() {
        game = new Game("test");
        input = new ArrayList<String>();
    }
    
    @Test
    public void addingBullets() {
        game.addBullet();
        ArrayList<Bullet> bullets = game.getBullets();
        assertEquals(1, bullets.size(), 0.01);
    }
    
    @Test
    public void deletingBulletOutOfBounds() {
        game.addBullet();
        game.updateBullets(4);
        game.destroyBullets();
        ArrayList<Bullet> bullets = game.getBullets();
        assertEquals(0, bullets.size(), 0.01);
    }
    
    @Test
    public void addingEnemies() {
        game.addEnemy();
        ArrayList<Enemy> enemies = game.getEnemies();
        assertEquals(1, enemies.size(), 0.01);
    }
    
    @Test
    public void deletingEnemyOutOfBounds() {
        game.addEnemy();
        game.updateEnemies(4);
        game.destroyEnemies();
        ArrayList<Enemy> enemies = game.getEnemies();
        assertEquals(0, enemies.size(), 0.01);
    }
    
    @Test
    public void playerMovesCorrectly() {
        input.add("LEFT");
        game.update(100000000, input);
        Player player = game.getPlayer();
        double posX = player.getPositionX();
        double posY = player.getPositionY();
        float dTime = game.getDeltaTime();
        assertEquals(400 - 600 * dTime, posX, 0.1);
        assertEquals(530, posY, 0.1);
    }
    
    @Test
    public void spacebarToShootWorks() {
        input.add("SPACE");
        game.update(1000000000, input);
        ArrayList<Bullet> bullets = game.getBullets();
        assertEquals(1, bullets.size(), 0.01);
    }
}
