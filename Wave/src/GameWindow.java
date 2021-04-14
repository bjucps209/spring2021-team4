import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import model.GameObjects.Player;
import model.GameObjects.Enemies.EnemyObject;
import model.Game;
import model.Wave;
import model.GameObjects.GameObject;
import model.Enums.EnemyTypes;


public class GameWindow {

    Wave w;
    static Player p;
    static Game g;

    static Scene s;

    @FXML
    public static void setScene(Scene s) {
        GameWindow.s = s;
    }

    @FXML Pane pane;

    @FXML
    public void initialize() {
        w = Wave.getInstance();
        w.gameStart();
        g = w.getGame();
        p = g.getCurrentLevel().getPlayer();
        spawnEntities();

        // Getting the game to update at 16.7ms or ~60fps
        var timer = new Timeline(
            new KeyFrame(Duration.millis(16.7), e -> {
                g.update();
            }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public void spawnEntities() {
        // code to combine all spawn function below
        spawnPlayer();
        spawnEnemies();
        spawnObstacles();
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

                break;
            case LASER:

                break;
            case SHAPESHIFTER:

                break;
            case TRACKER:

                break;
            default:

                break;
            }
        }
    }

    public void spawnObstacles() {
        
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