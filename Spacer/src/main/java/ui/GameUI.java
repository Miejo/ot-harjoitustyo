package ui;

import domain.Leaderboard;
import domain.Game;
import domain.Player;
import domain.Enemy;
import domain.Bullet;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;

public class GameUI {
    
    private final Canvas canvas;
    private final Scene scene;
    private final Scene endScene;
    private final Stage stage;
    private final Label endScoreText;
    private GraphicsContext gc;
    private Image playerSprite;
    private Image enemySprite;
    private Image bulletSprite;
    private String scoreText;
    private String nameText;
    private String color;
    private String name;
    private String controls;
    private double hue;
    private double saturation;
    
    private ArrayList<String> input;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private Player player;
    
    private Leaderboard leaderboard;
    
    public GameUI(Canvas canvas, Scene scene, Stage stage, Scene endScene, Label endScoreText, String color, Leaderboard leaderboard, String name, String controls) {
        this.canvas = canvas;
        this.scene = scene;
        this.stage = stage;
        this.endScene = endScene;
        this.endScoreText = endScoreText;
        this.color = color;
        this.leaderboard = leaderboard;
        this.name = name;
        this.controls = controls;
        init();
        setKeyPress();
    }
    
    private void init() {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        input = new ArrayList<>();
        playerSprite = new Image("spaceship.png");
        enemySprite = new Image("enemy.png");
        bulletSprite = new Image("bullet.png");
        scoreText = "Score: 0";
        nameText = "Name: " + name;
        hue = 0;
        saturation = 1;
        switch (color) {
            case "white":
                hue = 0;
                saturation = 0;
            case "red":
                hue = 0;
                break;
            case "blue":
                hue = (double) -2 / 3;
                break;
            case "yellow":
                hue = (double) 1 / 3;
                break;
            case "green":
                hue = (double) 2 / 3;
                break;
            case "orange":
                hue = (double) 1 / 6;
                break;
            case "purple":
                hue = (double) -1 / 6;
                break;
        }
    }
    
    public void run() {
        Game game = new Game(controls);
        new AnimationTimer()
        {
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, 800, 600);
                game.update(currentNanoTime, input);
                bullets = game.getBullets();
                enemies = game.getEnemies();
                player = game.getPlayer();
                scoreText = "Score: " + game.getScore();
                
                for (Bullet b : bullets) {
                    gc.drawImage(bulletSprite, b.getPositionX(), b.getPositionY());
                }
                
                for (Enemy e : enemies) {
                    gc.drawImage(enemySprite, e.getPositionX(), e.getPositionY());
                }
                
                ColorAdjust colorAdjust = new ColorAdjust(hue, saturation, 0, 1);
                gc.setEffect(colorAdjust);
                gc.drawImage(playerSprite, player.getPositionX(), player.getPositionY());
                gc.setEffect(null);
                gc.fillText(scoreText, 10, 10);
                gc.fillText(nameText, 10, 20);
                
                if (game.getEndGame()) {
                    leaderboard.addScore(name, game.getScore());
                    endScoreText.setText(scoreText);
                    stage.setScene(endScene);
                    this.stop();
                }
            }
        }.start();
    }
    
    private void setKeyPress() {
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