package domain;

public class Player {
    private double positionX;
    private final double positionY;
    private final double velocity;
    
    public Player() {
        velocity = 8;
        positionX = 400;
        positionY = 530;
    }
    
    public void moveLeft() {
        if (positionX > 0) {
            positionX -= velocity;
        }
    }
    
    public void moveRight() {
        if (positionX < 736) {
            positionX += velocity;
        }
    }
    
    public double getPositionX() {
        return positionX;
    }
    
    public double getPositionY() {
        return positionY;
    }
}