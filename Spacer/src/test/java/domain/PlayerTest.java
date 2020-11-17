package domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    
    public PlayerTest() {
    }

    @Test
    public void playerMoveLeft() {
        Player player = new Player();
        // PositionX in the start is 400
        player.moveLeft();
        // Changes positionX to 392
        assertEquals(392, player.getPositionX(), 0.01);
    }
    
    @Test
    public void playerMoveRight() {
        Player player = new Player();
        // PositionX in the start is 400
        player.moveRight();
        // Changes positionX to 408
        assertEquals(408, player.getPositionX(), 0.01);
    }
}