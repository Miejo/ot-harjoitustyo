package ui;

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
import javafx.scene.layout.FlowPane;

public class GameUI {
    
    private final Canvas canvas;
    private final Scene scene;
    private final Stage stage;
    private Scene endGameScene;
    private GraphicsContext gc;
    private Image playerSprite;
    private Image enemySprite;
    private Image bulletSprite;
    private String scoreText;
    
    private ArrayList<String> input;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private Player player;
    
    public GameUI (Canvas canvas, Scene scene, Stage stage) {
        this.canvas = canvas;
        this.scene = scene;
        this.stage = stage;
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
    }
    
    public void run() {
        Game game = new Game();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
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
                
                gc.drawImage(playerSprite, player.getPositionX(), player.getPositionY());
                gc.fillText(scoreText, 10, 10);
                
                if (game.getEndGame()){
                    Label gameOverText = new Label("GAME OVER");
                    Label endScoreText = new Label(scoreText);
                    FlowPane endGamePane = new FlowPane();
                    endGamePane.getChildren().addAll(gameOverText, endScoreText);
                    endGameScene = new Scene(endGamePane, 800, 600);
                    stage.setScene(endGameScene);
                    this.stop();
                }
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