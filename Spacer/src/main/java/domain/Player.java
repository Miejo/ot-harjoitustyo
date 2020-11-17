package domain;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {
    private Image image;
    private double positionX;
    private double positionY;
    private double velocity;
    
    public Player() {
        image = new Image("spaceship.png");
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
    
    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }
    
    public double getPositionX() {
        return positionX;
    }
    
    public double getPositionY() {
        return positionY;
    }
}