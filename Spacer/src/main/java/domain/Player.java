package domain;

/**
 * Luokka pelaajan oliolle.
 */
public class Player {
    private double positionX;
    private final double positionY;
    private final double velocity;
    
    /**
     * Konstruktori, joka alustaa nopeuden ja sijainnin.
     */
    public Player() {
        velocity = 600;
        positionX = 400;
        positionY = 530;
    }
    
    /**
     * Päivittää vasemmalle liikkumisen perustuen nopeuteen ja deltaTime:en.
     * @param deltaTime Kahden update-syklin väliin kuluva aika
     */
    public void moveLeft(float deltaTime) {
        if (positionX > 0) {
            positionX -= velocity * deltaTime;
        }
    }
    
    /**
     * Päivittää oikealle liikkumisen perustuen nopeuteen ja deltaTime:en
     * @param deltaTime 
     */
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