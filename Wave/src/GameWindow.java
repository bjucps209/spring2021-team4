import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import model.GameObjects.Player;
import model.GameObjects.Enemies.EnemyObject;
import model.Game;
import model.Wave;

public class GameWindow {

    Wave w;
    static Player p;
    static Game g;

    @FXML
    Pane pane;
    @FXML
    Label health;

    static Timeline timer;
    Timeline countDown;

    @FXML
    public void initialize() {

        w = Wave.getInstance();
        w.gameStart();
        g = w.getGame();
        p = g.getCurrentLevel().getPlayer();
        health.textProperty().bind(p.healthProperty().asString());
        spawnEntities();

        // Getting the game to update at 16.7ms or ~60fps
        timer = new Timeline(new KeyFrame(Duration.millis(16.7), e -> {
            g.update();
            var s = pane.getChildren();
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        // Platform.exit();

        // Label to represent the timer
        Label lblTimer = new Label();
        g.getCurrentLevel().setRemainingTime(60);
        lblTimer.textProperty().bind(Bindings.createStringBinding( () -> String.valueOf(g.getCurrentLevel().getRemainingTime()), g.getCurrentLevel().remainingTimeProperty()));
        pane.getChildren().add(lblTimer);
        lblTimer.relocate(0, 30);
        // timer to connect to the countdown in game.
        countDown = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            g.getCurrentLevel().setRemainingTime(g.getCurrentLevel().getRemainingTime() - 1);
        }));
        countDown.setCycleCount(60);
        countDown.play();
    }

    public void spawnEntities() {
        // code to combine all spawn function below
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