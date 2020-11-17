package domain;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Game{
    
    private GraphicsContext gc;
    private final Canvas canvas;
    private final Scene scene;
    private Player player;
    private ArrayList<String> input;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> deleteBullets;
    private ArrayList<Enemy> enemies;
    private ArrayList<Enemy> deleteEnemies;
    private Image playerSprite;
    private Image enemySprite;
    private Image bulletSprite;
    // private long prevNanoTime;
    
    public Game (Canvas canvas, Scene scene) {
        this.canvas = canvas;
        this.scene = scene;
    }
    
    public void initGame(){
        gc = canvas.getGraphicsContext2D();
        playerSprite = new Image("spaceship.png");
        enemySprite = new Image("enemy.png");
        bulletSprite = new Image("bullet.png");
        player = new Player();
        input = new ArrayList<>();
        bullets = new ArrayList<>();
        deleteBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        deleteEnemies = new ArrayList<>();
        enemies.add(new Enemy());
        setKeyPress();
    }
    
    public void update(long currentNanoTime){
        gc.clearRect(0, 0, 800, 600);

        if (input.contains("LEFT")){
            player.moveLeft();
        }
        if (input.contains("RIGHT")){
            player.moveRight();
        }
        if (input.contains("SPACE")){
            bullets.add(new Bullet(player.getPositionX(), player.getPositionY()));
        }

        for (Bullet b : deleteBullets) {
            bullets.remove(b);
        }

        for (Enemy e : deleteEnemies) {
            enemies.remove(e);
        }

        for (Bullet b : bullets) {
            b.update();
            gc.drawImage(bulletSprite, b.getPositionX(), b.getPositionY());
            if (b.outOfBounds()) {
                deleteBullets.add(b);
            }
            for (Enemy e :enemies){
                if (b.getCollisionBox().intersects(e.getCollisionBox())) {
                    deleteBullets.add(b);
                    deleteEnemies.add(e);
                }
            }
        }

        for (Enemy e : enemies) {
            e.update();
            gc.drawImage(enemySprite, e.getPositionX(), e.getPositionY());
            if (e.outOfBounds()) {
                deleteEnemies.add(e);
            }
        }

        gc.drawImage(playerSprite, player.getPositionX(), player.getPositionY());
    }
    
    public void setKeyPress(){
        scene.setOnKeyPressed(
            new EventHandler<KeyEvent>(){
                public void handle(KeyEvent e) {
                    String code = e.getCode().toString();

                    if (!input.contains(code)) {
                        input.add(code);
                    }
                }
            }
        );
        scene.setOnKeyReleased(
            new EventHandler<KeyEvent>(){
                public void handle(KeyEvent e){
                    String code = e.getCode().toString();
                    input.remove(code);
                }
            }
        );
    }
}