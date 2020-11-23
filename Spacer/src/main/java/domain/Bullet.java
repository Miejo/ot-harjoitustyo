package domain;

import javafx.geometry.Rectangle2D;

public class Bullet {
    private double positionX;
    private double positionY;
    private double velocity;
    private Rectangle2D collisionBox;
    
    public Bullet(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        velocity = 8;
        collisionBox = new Rectangle2D(positionX, positionY, 8, 8);
    }
    
    public void update() {
        positionY -= velocity;
        collisionBox = new Rectangle2D(positionX, positionY, 8, 8);
    }
    
    public boolean outOfBounds() {
        return positionY < 0;
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
