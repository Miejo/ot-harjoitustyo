package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import domain.Player;
import domain.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;

public class GameUI extends Application {
    
    private Scene startMenuScene;
    //private Scene settingsScene;
    //private Scene leaderboardScene;
    private Scene gameScene;
    
    @Override
    public void start(Stage mainStage) {
        // Start menu components
        Label titleText = new Label("GAME TITLE");
        Button startButton = new Button("Start");
        Button settingsButton = new Button("Settings");
        Button leaderboardButton = new Button("Leaderboard");
        
        FlowPane startMenuPane = new FlowPane();
        startMenuPane.getChildren().addAll(titleText, startButton, settingsButton, leaderboardButton);
        
        startMenuScene = new Scene(startMenuPane, 800, 600);
        
        // Game components
        Group gameGroup = new Group();
        Canvas gameCanvas = new Canvas(800,600);
        Player player = new Player();
        
        gameGroup.getChildren().addAll(gameCanvas);
        
        gameScene = new Scene(gameGroup, 800, 600);
        
        startButton.setOnAction(e->{
            mainStage.setScene(gameScene);
            Game game = new Game(gameCanvas, gameScene);
            game.initGame();
            new AnimationTimer()
            {
                public void handle(long currentNanoTime)
                {
                    game.update(currentNanoTime);
                }
            }.start();
        });
        
        // Set starting scene
        mainStage.setTitle("GAME");
        mainStage.setScene(startMenuScene);
        mainStage.show();
    }
    
    public static void main(String[] args) {
        launch(GameUI.class);
    }
}