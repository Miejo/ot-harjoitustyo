package ui;

import dao.FileSettingsDao;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class MainUI extends Application {
    
    private Scene startMenuScene;
    private Scene settingsScene;
    private Scene leaderboardScene;
    private Scene endScene;
    private Scene gameScene;
    private final Font titleFont = new Font("Arial", 42);
    private FileSettingsDao settingsDao;
    private ArrayList<String> colors;
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String settingsFile = properties.getProperty("settingsFile");
        settingsDao = new FileSettingsDao(settingsFile);
        colors = new ArrayList<>();
        colors.addAll(Arrays.asList("white", "red", "blue", "yellow", "green", "orange", "purple"));
    }
    
    @Override
    public void start(Stage mainStage) {
        // Start menu components
        Label titleText = new Label("Spacer");
        titleText.setFont(titleFont);
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
        GridPane.setHalignment(titleText, HPos.CENTER);
        startMenuPane.setVgap(10);
        startMenuPane.setVgap(10);
        
        startMenuScene = new Scene(startMenuPane, 800, 600);
        // Settings components
        HashMap<String, String> settings = settingsDao.getSettings();
        Label settingsTitleText = new Label("Settings");
        settingsTitleText.setFont(titleFont);
        Label settingsNameText = new Label("Name: ");
        TextField settingsNameField = new TextField(settings.get("username"));
        Label settingsColorLabel = new Label("Color:");
        Label settingsColorValue = new Label(settings.get("color"));
        Button settingsColorLeft = new Button("<");
        Button settingsColorRight = new Button(">");
        Label settingsControlsLabel = new Label("Controls:");
        ToggleButton settingsControlsA = new ToggleButton("Arrow keys");
        ToggleButton settingsControlsB = new ToggleButton("WASD");
        ToggleGroup settingsControlsSel = new ToggleGroup();
        settingsControlsA.setToggleGroup(settingsControlsSel);
        settingsControlsB.setToggleGroup(settingsControlsSel);
        if ("arrow".equals(settings.get("controls"))) {
            settingsControlsA.setSelected(true);
        } else {
            settingsControlsB.setSelected(true);
        }
        
        Button settingsBackButton = new Button("Back");
        settingsBackButton.setMaxSize(100, 200);
        Button settingsSaveButton = new Button("Save");
        settingsSaveButton.setMaxSize(100, 200);
        
        GridPane settingsPane = new GridPane();
        settingsPane.add(settingsTitleText, 0, 0);
        settingsPane.add(settingsNameText, 0, 1);
        settingsPane.add(settingsNameField, 0, 2);
        settingsPane.add(settingsColorLabel, 0, 3);
        settingsPane.add(settingsColorValue, 1, 4);
        settingsPane.add(settingsColorLeft, 0, 4);
        settingsPane.add(settingsColorRight, 2, 4);
        settingsPane.add(settingsControlsLabel, 0, 5);
        settingsPane.add(settingsControlsA, 0, 6);
        settingsPane.add(settingsControlsB, 1, 6);
        settingsPane.add(settingsBackButton, 0, 7);
        settingsPane.add(settingsSaveButton, 1, 7);
        
        
        settingsPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(settingsTitleText, HPos.CENTER);
        GridPane.setHalignment(settingsColorValue, HPos.CENTER);
        settingsPane.setVgap(10);
        settingsPane.setVgap(10);
        
        settingsScene = new Scene(settingsPane, 800, 600);
        
        // Leaderboard components
        Label leaderboardTitleText = new Label("Leaderboard");
        leaderboardTitleText.setFont(titleFont);
        Button leaderboardBackButton = new Button("Back");
        leaderboardBackButton.setMaxSize(150, 200);
        
        GridPane leaderboardPane = new GridPane();
        leaderboardPane.add(leaderboardTitleText, 0, 0);
        leaderboardPane.add(leaderboardBackButton, 0, 1);
        
        leaderboardPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(leaderboardTitleText, HPos.CENTER);
        leaderboardPane.setVgap(10);
        leaderboardPane.setVgap(10);
        
        leaderboardScene = new Scene(leaderboardPane, 800, 600);
        
        // Game components
        Group gameGroup = new Group();
        Canvas gameCanvas = new Canvas(800, 600);
        
        gameGroup.getChildren().addAll(gameCanvas);
        gameScene = new Scene(gameGroup, 800, 600);
        
        // End game components
        Label endTitleText = new Label("Game Over");
        endTitleText.setFont(titleFont);
        Label endScoreText = new Label("");
        endScoreText.setFont(titleFont);
        Button endBackButton = new Button("Back to menu");
        endBackButton.setMaxSize(150, 200);
        
        GridPane endPane = new GridPane();
        endPane.add(endTitleText, 0, 0);
        endPane.add(endScoreText, 0, 1);
        endPane.add(endBackButton, 0, 2);
        
        endPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(endTitleText, HPos.CENTER);
        GridPane.setHalignment(endScoreText, HPos.CENTER);
        endPane.setVgap(10);
        endPane.setHgap(10);
        
        endScene = new Scene(endPane, 800, 600);
        
        // Button logic
        startButton.setOnAction(e-> {
            mainStage.setScene(gameScene);
            GameUI gameUI = new GameUI(gameCanvas, gameScene, mainStage, endScene, endScoreText, settings.get("color"));
            gameUI.run();
        });
        
        settingsButton.setOnAction(e-> {
            mainStage.setScene(settingsScene);
        });
        
        leaderboardButton.setOnAction(e-> {
            mainStage.setScene(leaderboardScene);
        });
        
        settingsBackButton.setOnAction(e-> {
            mainStage.setScene(startMenuScene);
            settingsNameField.setText(settings.get("username"));
            settingsColorValue.setText(settings.get("color"));
            if ("arrow".equals(settings.get("controls"))) {
                settingsControlsA.setSelected(true);
            } else {
                settingsControlsB.setSelected(true);
            }
        });
        
        leaderboardBackButton.setOnAction(e-> {
            mainStage.setScene(startMenuScene);
        });
        
        endBackButton.setOnAction(e-> {
            mainStage.setScene(startMenuScene);
        });
        
        settingsColorLeft.setOnAction(e-> {
            int i = colors.indexOf(settingsColorValue.getText());
            if (i > 0) {
                settingsColorValue.setText(colors.get(i - 1));
            }
        });
        
        settingsColorRight.setOnAction(e-> {
            int i = colors.indexOf(settingsColorValue.getText());
            if (i < colors.size() - 1) {
                settingsColorValue.setText(colors.get(i + 1));
            }
        });
        
        settingsSaveButton.setOnAction(e-> {
            settings.put("username", settingsNameField.getText());
            settings.put("color", settingsColorValue.getText());
            if (settingsControlsA.isSelected()) {
                settings.put("controls", "arrow");
            } else {
                settings.put("controls", "WASD");
            }
            settingsDao.create(settings);
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
