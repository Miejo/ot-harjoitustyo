package ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class MainUI extends Application {
    
    private Scene startMenuScene;
    private Scene settingsScene;
    private Scene leaderboardScene;
    //private Scene endGameScene;
    private Scene gameScene;
    
    @Override
    public void start(Stage mainStage) {
        // Start menu components
        Label titleText = new Label("Spacer");
        titleText.setFont(new Font("Arial", 42));
        Button startButton = new Button("Start");
        startButton.setMaxSize(150, 200);
        Button settingsButton = new Button("Settings");
        settingsButton.setMaxSize(150, 200);
        Button leaderboardButton = new Button("Leaderboard");
        leaderboardButton.setMaxSize(150, 200);
        
        GridPane startMenuPane = new GridPane();
        startMenuPane.add(titleText, 0, 0);
        startMenuPane.add(startButton, 0, 1);
        startMenuPane.add(settingsButton, 0, 2);
        startMenuPane.add(leaderboardButton, 0, 3);
        
        startMenuPane.setAlignment(Pos.CENTER);
        startMenuPane.setHalignment(titleText, HPos.CENTER);
        startMenuPane.setVgap(10);
        startMenuPane.setVgap(10);
        
        startMenuScene = new Scene(startMenuPane, 800, 600);
        
        // Settings components
        Label settingsTitleText = new Label("Settings");
        settingsTitleText.setFont(new Font("Arial", 42));
        Button settingsBackButton = new Button("Back");
        settingsBackButton.setMaxSize(150, 200);
        
        GridPane settingsPane = new GridPane();
        settingsPane.add(settingsTitleText, 0, 0);
        settingsPane.add(settingsBackButton, 0, 1);
        
        settingsPane.setAlignment(Pos.CENTER);
        settingsPane.setHalignment(settingsTitleText, HPos.CENTER);
        settingsPane.setVgap(10);
        settingsPane.setVgap(10);
        
        settingsScene = new Scene(settingsPane, 800, 600);
        
        // Leaderboard components
        Label leaderboardTitleText = new Label("Leaderboard");
        leaderboardTitleText.setFont(new Font("Arial", 42));
        Button leaderboardBackButton = new Button("Back");
        leaderboardBackButton.setMaxSize(150, 200);
        
        GridPane leaderboardPane = new GridPane();
        leaderboardPane.add(leaderboardTitleText, 0, 0);
        leaderboardPane.add(leaderboardBackButton, 0, 1);
        
        leaderboardPane.setAlignment(Pos.CENTER);
        leaderboardPane.setHalignment(leaderboardTitleText, HPos.CENTER);
        leaderboardPane.setVgap(10);
        leaderboardPane.setVgap(10);
        
        leaderboardScene = new Scene(leaderboardPane, 800, 600);
        
        // Game components
        Group gameGroup = new Group();
        Canvas gameCanvas = new Canvas(800,600);
        
        gameGroup.getChildren().addAll(gameCanvas);
        
        gameScene = new Scene(gameGroup, 800, 600);
        
        // Button logic
        startButton.setOnAction(e->{
            mainStage.setScene(gameScene);
            GameUI gameUI = new GameUI(gameCanvas, gameScene, mainStage);
            gameUI.run();
        });
        
        settingsButton.setOnAction(e->{
           mainStage.setScene(settingsScene);
        });
        
        leaderboardButton.setOnAction(e->{
           mainStage.setScene(leaderboardScene);
        });
        
        settingsBackButton.setOnAction(e->{
           mainStage.setScene(startMenuScene);
        });
        
        leaderboardBackButton.setOnAction(e->{
           mainStage.setScene(startMenuScene);
        });
        
        // Set starting scene
        mainStage.setTitle("Spacer");
        mainStage.setScene(startMenuScene);
        mainStage.show();
    }
    
    public static void main(String[] args) {
        launch(MainUI.class);
    }
}
