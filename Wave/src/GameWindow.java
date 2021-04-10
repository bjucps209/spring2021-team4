import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import model.GameObjects.Player;
import model.Game;
import model.Wave;
import model.GameObjects.EnemyObject;

public class GameWindow {

    Wave w;
    Player p;
    Game g;

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
    }

    public void spawnEntities() {
        // code to combine all spawn function below
        spawnPlayer();
        spawnEnemies();
        spawnObstacles();
    }

    public void spawnPlayer() {
        ImageView playerImage = new ImageView();
        playerImage.setId("player");
        playerImage.layoutXProperty().bind(p.xProperty());
        playerImage.layoutYProperty().bind(p.yProperty());
    }

    public void spawnEnemies() {
        for (EnemyObject o : g.getLevels().get(g.getLevelNum()).getEnemies()) {
            switch (o.getType()) {
            case BOUNCER:

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
        // code to spawn obstacles
    }

    public void moveUp() {
        p.moveUp();
        g.update();
    }

    public void moveDown() {
        p.moveDown();
        g.update();
    }

    public void moveRight() {
        p.moveRight();
        g.update();
    }

    public void moveLeft() {
        p.moveLeft();
        g.update();
    }
    
    public void moveNeutral() {
        p.moveNeutral();
        g.update();
    }
}