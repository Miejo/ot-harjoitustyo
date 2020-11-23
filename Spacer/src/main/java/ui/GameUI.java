package ui;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import javafx.scene.input.KeyEvent;

import domain.Game;
import domain.Player;
import domain.Enemy;
import domain.Bullet;

public class GameUI {
    
    private final Canvas canvas;
    private final Scene scene;
    private GraphicsContext gc;
    private Image playerSprite;
    private Image enemySprite;
    private Image bulletSprite;
    private ArrayList<String> input;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private Player player;
    
    public GameUI (Canvas canvas, Scene scene) {
        this.canvas = canvas;
        this.scene = scene;
        init();
        setKeyPress();
    }
    
    private void init() {
        gc = canvas.getGraphicsContext2D();
        input = new ArrayList<>();
        playerSprite = new Image("spaceship.png");
        enemySprite = new Image("enemy.png");
        bulletSprite = new Image("bullet.png");
    }
    
    public void run() {
        Game game = new Game(input);
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                gc.clearRect(0, 0, 800, 600);
                game.update(currentNanoTime, input);
                bullets = game.getBullets();
                enemies = game.getEnemies();
                player = game.getPlayer();
                
                for (Bullet b : bullets) {
                    gc.drawImage(bulletSprite, b.getPositionX(), b.getPositionY());
                }
                
                for (Enemy e : enemies) {
                    gc.drawImage(enemySprite, e.getPositionX(), e.getPositionY());
                }
                
                gc.drawImage(playerSprite, player.getPositionX(), player.getPositionY());
            }
        }.start();
    }
    
    private void setKeyPress(){
        scene.setOnKeyPressed((KeyEvent e) -> {
            String code = e.getCode().toString();
            
            if (!input.contains(code)) {
                input.add(code);
            }
        });
        
        scene.setOnKeyReleased((KeyEvent e) -> {
            String code = e.getCode().toString();
            input.remove(code);
        });
    }
}