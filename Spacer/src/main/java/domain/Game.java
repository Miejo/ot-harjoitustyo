package domain;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    // private long prevNanoTime;
    
    public Game (Canvas canvas, Scene scene) {
        this.canvas = canvas;
        this.scene = scene;
    }
    
    public void initGame(){
        gc = canvas.getGraphicsContext2D();
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
            b.render(gc);
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
            e.render(gc);
            if (e.outOfBounds()) {
                deleteEnemies.add(e);
            }
        }

        player.render(gc);
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