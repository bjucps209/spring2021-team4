import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.GameObjects.GameObject;
import model.GameObjects.Player;
import model.GameObjects.Enemies.EnemyObject;
import model.GameObjects.Powerups.PowerUp;
import model.Game;
import model.HighScore;
import model.HighScoreList;
import model.Wave;

public class GameWindow {

    static Wave w;
    static Player p;
    static Game g;

    static HighScoreList highScoreList = new HighScoreList();
    static boolean pauseState = false;

    @FXML
    Pane pane;

    static Timeline timer;
    Timeline countDown;

    @FXML
    public void initialize() {

        w = Wave.getInstance();
        w.gameStart();
        g = w.getGame();
        p = g.getCurrentLevel().getPlayer();
        spawnEntities();
        
        //pane dimensions
        pane.setPrefSize(1000, 800);

        // Getting the game to update at 16.7ms or ~60fps
        timer = new Timeline(new KeyFrame(Duration.millis(16.7), e -> {
            g.update();
            var s = pane.getChildren();
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        // Platform.exit();

        // Label to represent the timer
        Label lblTime = new Label("TIME REMAINING");
        Label lblTimer = new Label();
        g.getCurrentLevel().setRemainingTime(60);
        lblTimer.textProperty().bind(g.getCurrentLevel().remainingTimeProperty().asString());
        pane.getChildren().add(lblTime);
        pane.getChildren().add(lblTimer);
        lblTime.relocate(0, 60);
        lblTimer.relocate(0, 80);
        // timer to connect to the countdown in game.
        countDown = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            g.getCurrentLevel().setRemainingTime(g.getCurrentLevel().getRemainingTime() - 1);
            w.setCoins(w.getCoins() + 10);
        }));
        countDown.setCycleCount(60);
        countDown.play();

        // health label and bar with binding
        Label lblHealth = new Label("HEALTH");

        ProgressBar healthBar = new ProgressBar();
        healthBar.progressProperty().bind(Bindings.createDoubleBinding(() -> (double) p.getHealth() / 100, p.healthProperty()));
        pane.getChildren().add(lblHealth);
        pane.getChildren().add(healthBar);
        healthBar.relocate(0, 30);

        // Score label and binding
        Label lblSCORE = new Label("SCORE");
        Label lblScore = new Label();
        lblScore.textProperty().bind(w.coinsProperty().asString());
        pane.getChildren().add(lblSCORE);
        pane.getChildren().add(lblScore);
        lblSCORE.relocate(900, 0);
        lblScore.relocate(900, 20);
    }

    

    public void spawnEntities() {
        // code to combine all spawn function below
        spawnObstecles();
        spawnPowerups();
        spawnPlayer();
        spawnEnemies();
    }

    // spawns the player image and then binds that image to the player object
    public void spawnPlayer() {
        Image playerImage = new Image("./Images/playerShip1_blue.png");
        ImageView playerImageView = new ImageView(playerImage);
        playerImageView.setFitWidth(p.getWidth());
        playerImageView.setFitHeight(p.getHeight());
        playerImageView.layoutXProperty().bind(p.xProperty());
        playerImageView.layoutYProperty().bind(p.yProperty());
        pane.getChildren().add(playerImageView);
    }

    public void spawnEnemies() {
        for (EnemyObject o : g.getLevels().get(g.getLevelNum()).getEnemies()) {
            switch (o.getType()) {
            case BOUNCER:
                Image bouncerImage = new Image("./Images/enemyBlack4.png");
                ImageView bouncerImageView = new ImageView(bouncerImage);
                bouncerImageView.setFitWidth(o.getWidth());
                bouncerImageView.setFitHeight(o.getHeight());
                pane.getChildren().add(bouncerImageView);
                bouncerImageView.layoutXProperty().bind(o.xProperty());
                bouncerImageView.layoutYProperty().bind(o.yProperty());
                break;
            case GHOST:
                Image ghostImage = new Image("./Images/enemyBlack2.png");
                ImageView ghostImageView = new ImageView(ghostImage);
                ghostImageView.setFitWidth(o.getWidth());
                ghostImageView.setFitHeight(o.getHeight());
                pane.getChildren().add(ghostImageView);
                ghostImageView.layoutXProperty().bind(o.xProperty());
                ghostImageView.layoutYProperty().bind(o.yProperty());
                break;
            case LASER:
                Image laserImage = new Image("./Images/cockpitGreen_0.png");
                ImageView laserImageView = new ImageView(laserImage);
                laserImageView.setFitWidth(o.getWidth());
                laserImageView.setFitHeight(o.getHeight());
                pane.getChildren().add(laserImageView);
                laserImageView.layoutXProperty().bind(o.xProperty());
                laserImageView.layoutYProperty().bind(o.yProperty());
                break;
            case SHAPESHIFTER:
                Image shapeshifterImage = new Image("./Images/enemyBlack4.png");
                ImageView shapeshifterImageView = new ImageView(shapeshifterImage);
                shapeshifterImageView.setFitWidth(o.getWidth());
                shapeshifterImageView.setFitHeight(o.getHeight());
                pane.getChildren().add(shapeshifterImageView);
                shapeshifterImageView.layoutXProperty().bind(o.xProperty());
                shapeshifterImageView.layoutYProperty().bind(o.yProperty());
                break;
            case TRACKER:
                Image trackerImage = new Image("./Images/enemyBlack1.png");
                ImageView trackerImageView = new ImageView(trackerImage);
                trackerImageView.setFitWidth(o.getWidth());
                trackerImageView.setFitHeight(o.getHeight());
                pane.getChildren().add(trackerImageView);
                trackerImageView.layoutXProperty().bind(o.xProperty());
                trackerImageView.layoutYProperty().bind(o.yProperty());
                break;
            default:

                break;
            }
        }
    }

    public void spawnObstacles() {
        // Image obsteclImage = new Image("./Images/block_square.png");
        // ImageView obstImageView = new ImageView(obsteclImage);
        // obstImageView.setLayoutX(500);
        // obstImageView.setLayoutY(300);
        // pane.getChildren().add(obstImageView);
    }

    public void spawnPowerups() {
        // Image powerupsImage = new Image("./Images/powerupBlue_bolt.png");
        // ImageView powerupsImageView = new ImageView(powerupsImage);
        // powerupsImageView.setLayoutX(600);
        // powerupsImageView.setLayoutY(300);
        // pane.getChildren().add(powerupsImageView);
    }

    // Method for pausing the game and ending the game
    
    public static void pause() {
        if (pauseState == false) {
            pauseState = true;
            for (EnemyObject item : g.getCurrentLevel().getEnemies()) {
                item.pause();
            }
            timer.pause();

            // Opens window to allow player to enter their name
            VBox vboxName = new VBox();
            vboxName.setPadding(new Insets(10));
            vboxName.setSpacing(10);
            vboxName.setAlignment(Pos.CENTER);

            Scene nameScene = new Scene(vboxName, 800, 600);
            Stage nameStage = new Stage();
            nameStage.setScene(nameScene); // set the scene
            nameStage.setTitle("Name Menu");
            nameStage.setAlwaysOnTop(true);
            nameStage.show();

            nameScene.getStylesheets().add("GameWindow.css");

            TextField nameField = new TextField();
            Label lblName = new Label();
            lblName.setText("Enter Your Name:");
            vboxName.getChildren().add(lblName);
            vboxName.getChildren().add(nameField);
            nameField.requestFocus();
            nameScene.setOnKeyPressed(key -> {
                KeyCode keyCode = key.getCode();
                if (keyCode.equals(KeyCode.ENTER)) {
                    highScoreList.getList().add(new HighScore(nameField.getText(), w.getCoins()));
                    nameStage.close();
                    pauseState = false;
                    for (EnemyObject item : g.getCurrentLevel().getEnemies()) {
                        item.start();
                    }
                    // this is where all the saving gets excecuted
                    highScoreList.save();
                    timer.play();
                }
            });
        }
        // } else {
        //     for (EnemyObject item : g.getCurrentLevel().getEnemies()) {
        //         item.start();
        //     }
        // }
    }

    // close the timer
    public static void onClosed() {
        timer.stop();

    }

    public static void moveUp() {
        if (p.getDy() == 0) {
            p.moveUp();
            g.update();
        }
    }

    public static void moveDown() {
        if (p.getDy() == 0) {
            p.moveDown();
            g.update();
        }
    }

    public static void moveRight() {
        if (p.getDx() == 0) {
            p.moveRight();
            g.update();
        }
    }

    public static void moveLeft() {
        if (p.getDx() == 0) {
            p.moveLeft();
            g.update();
        }
    }

    public static void moveNeutral() {
        p.moveNeutral();
        g.update();
    }
}