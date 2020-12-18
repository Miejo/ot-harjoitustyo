package domain;

import javafx.geometry.Rectangle2D;

/**
 * Luokka ammuksille.
 */
public class Bullet {
    private double positionX;
    private double positionY;
    private double velocity;
    private Rectangle2D collisionBox;
    
    /**
     * Konstruktori, joka asettaa sijainnin, nopeuden ja törmäyksessä käytettävän neliön.
     * @param positionX Pelaajan sijannista riippuva X-akselin sijainti
     * @param positionY Pelaajan sijannista riippuva Y-akselin sijainti
     */
    public Bullet(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        velocity = 600;
        collisionBox = new Rectangle2D(positionX, positionY, 8, 8);
    }
    
    /**
     * Metodi päivittää ammuksen sijainnin ja törmäysneliön perustuen nopeuteen ja deltaTime:en.
     * @param deltaTime Kahden update-syklin väliin kuluva aika
     */
    public void update(float deltaTime) {
        positionY -= velocity * deltaTime;
        collisionBox = new Rectangle2D(positionX, positionY, 8, 8);
    }
    
    /**
     * Metodi tarkistaa onko ammus ruudun ulkopuolella.
     * @return true, jos yläreunan ulkopuolella, muuten false
     */
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
