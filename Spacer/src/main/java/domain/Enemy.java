package domain;

import javafx.geometry.Rectangle2D;

public class Enemy {
    private double positionX;
    private double positionY;
    private double velocity;
    private Rectangle2D collisionBox;
    
    public Enemy() {
        velocity = 2;
        positionX = 600;
        positionY = 10;
        collisionBox = new Rectangle2D(positionX, positionY, 64, 64);
    }
    
    public void update() {
        positionY += velocity;
        collisionBox = new Rectangle2D(positionX, positionY, 64, 64);
    }
    
    public boolean outOfBounds() {
        if (positionY > 600) {
            return true;
        }
        return false;
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
