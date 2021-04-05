import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.Wave;
import model.GameObjects.Player;


public class MainWindow {
    Wave w;

    @FXML
    public void initialize() {
        w = Wave.getInstance();
        w.gameStart();
    }

    public void onDisplayInfo() {
        Player p = w.getGame().getCurrentLevel().getPlayer();
        p.moveDown();
        p.moveLeft();
        w.getGame().update();
        System.out.println(p.toString());
    }
}
