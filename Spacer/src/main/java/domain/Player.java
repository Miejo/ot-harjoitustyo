package domain;

public class Player {
    private double positionX;
    private final double positionY;
    private final double velocity;
    
    public Player() {
        velocity = 600;
        positionX = 400;
        positionY = 530;
    }
    
    public void moveLeft(float deltaTime) {
        if (positionX > 0) {
            positionX -= velocity * deltaTime;
        }
    }
    
    public void moveRight(float deltaTime) {
        if (positionX < 736) {
            positionX += velocity * deltaTime;
        }
    }
    
    public double getPositionX() {
        return positionX;
    }
    
    public double getPositionY() {
        return positionY;
    }
}