package domain;

import javafx.geometry.Rectangle2D;
import java.util.Random;

public class Enemy {
    private double positionX;
    private double positionY;
    private final double velocity;
    private Rectangle2D collisionBox;
    private Random rand;
    
    public Enemy() {
        velocity = 150;
        rand = new Random();
        positionX = rand.nextInt(737);
        positionY = 10;
        collisionBox = new Rectangle2D(positionX, positionY, 64, 64);
    }
    
    public void update(float deltaTime) {
        positionY += velocity * deltaTime;
        collisionBox = new Rectangle2D(positionX, positionY, 64, 64);
    }
    
    public boolean outOfBounds() {
        return positionY > 600;
    }
    
    public Rectangle2D getCollisionBox() {
        return collisionBox;
    }
    
    public double getPositionX() {
        return positionX;
    }
    
    public double getPositionY() {
        return positionY;
    }
}