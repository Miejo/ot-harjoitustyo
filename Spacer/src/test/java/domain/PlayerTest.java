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
        player.moveLeft(0.1f);
        // Changes positionX to 340
        assertEquals(340, player.getPositionX(), 0.01);
    }
    
    @Test
    public void playerMoveRight() {
        Player player = new Player();
        // PositionX in the start is 400
        player.moveRight(0.1f);
        // Changes positionX to 460
        assertEquals(460, player.getPositionX(), 0.01);
    }
}