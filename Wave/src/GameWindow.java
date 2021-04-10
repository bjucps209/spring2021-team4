import javafx.fxml.FXML;
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
}
