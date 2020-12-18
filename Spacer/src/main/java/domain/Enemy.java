package domain;

import javafx.geometry.Rectangle2D;
import java.util.Random;

/**
 * Luokka vihollisille.
 */
public class Enemy {
    private double positionX;
    private double positionY;
    private final double velocity;
    private Rectangle2D collisionBox;
    private Random rand;
    
    /**
     * Konstruktori, joka antaa viholliselle satunnaisen sijainnin X-akselilla ja alustaa muut tiedot
     */
    public Enemy() {
        velocity = 150;
        rand = new Random();
        positionX = rand.nextInt(737);
        positionY = 10;
        collisionBox = new Rectangle2D(positionX, positionY, 64, 64);
    }
    
    /**
     * Metodi päivittää vihollisen sijainnin ja törmäysneliön perustuen nopeuteen ja deltaTime:en.
     * @param deltaTime Kahden update-syklin väliin kuluva aika
     */
    public void update(float deltaTime) {
        positionY += velocity * deltaTime;
        collisionBox = new Rectangle2D(positionX, positionY, 64, 64);
    }
    
    /**
     * Metodi tarkistaa onko vihollinen ruudun ulkopuolella.
     * @return true, jos alkareunan alapuolella, muuten false
     */
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