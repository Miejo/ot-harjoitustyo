package domain;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet {
    private Image image;
    private double positionX;
    private double positionY;
    private double velocity;
    private Rectangle2D collisionBox;
    
    public Bullet(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        image = new Image("bullet.png");
        velocity = 8;
        collisionBox = new Rectangle2D(positionX, positionY, 8, 8);
    }
    
    public void update() {
        positionY -= velocity;
        collisionBox = new Rectangle2D(positionX, positionY, 8, 8);
    }
    
    public boolean outOfBounds() {
        if (positionY < 0) {
            return true;
        }
        return false;
    }
    
    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }
    
    public Rectangle2D getCollisionBox() {
        return collisionBox;
    }
}
