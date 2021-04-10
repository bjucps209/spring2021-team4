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
        spawnEntities();
        s.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                if (k.getEventType() == KeyEvent.KEY_PRESSED) {
                    switch (k.getCode()) {
                        case UP:
                            p.moveUp();
                            g.update();
                        case DOWN:
                            p.moveDown();
                            g.update();
                        case LEFT:
                            p.moveLeft();
                            g.update();
                        case RIGHT:
                            p.moveRight();
                            g.update();
                    }
                }
            }
        });
        s.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                p.moveNeutral();
                g.update();
            }
        });
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
        playerImage.layoutXProperty().bind(p.xProperty);
        playerImage.layoutYProperty().bind(p.yProperty);
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
}