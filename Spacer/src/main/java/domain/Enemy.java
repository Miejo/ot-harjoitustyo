package domain;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy {
    private Image image;
    private double positionX;
    private double positionY;
    private double velocity;
    private Rectangle2D collisionBox;
    
    public Enemy() {
        image = new Image("enemy.png");
        velocity = 2;
        positionX = 600;
        positionY = 10;
        collisionBox = new Rectangle2D(positionX, positionY, 64, 64);
    }
    
    public void update() {
        positionY += velocity;
        collisionBox = new Rectangle2D(positionX, positionY, 64, 64);
    }
    
    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
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
}
