import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.GameObjects.Player;
import model.Game;
import model.Wave;
import model.GameObjects.EnemyObject;
import model.GameObjects.Player;

public class GameWindow {

    Wave w;
    Player p;
    Game g;

    @FXML
    public void initialize() {
        w = Wave.getInstance();
        w.gameStart();
        g = w.getGame();
        spawnEntities();
    }

    public void spawnEntities() {
        // code to combine all spawn function below
        spawnPlayer();
        spawnEnemies();
        spawnObstacles();
    }

    public void spawnPlayer() {
        // Code to spawn gui player
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

    // This will detect whether a movement key has been pressed or not
    void onKeyPressed(KeyEvent k) {
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

    // this will reset the speed of the player object whenever you release a key
    void onKeyReleased(KeyEvent k) {
        if (k.getEventType() == KeyEvent.KEY_RELEASED) {
            p.moveNeutral();
            g.update();
        }
    }
}