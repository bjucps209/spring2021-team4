import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Wave;
import model.GameObjects.Player;


public class MainWindow {
    Wave w;

    @FXML
    public void initialize() {
        w = Wave.getInstance();
        w.gameStart();
    }

    public void onNewGameClicked() throws IOException {
        // opens up new window which is GameWindow
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        Scene scene = new Scene(loader.load());
    
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Wave");
        stage.show();
    }

    public void onDisplayInfo() {
        Player p = w.getGame().getCurrentLevel().getPlayer();
        p.moveDown();
        p.moveLeft();
        w.getGame().update();
        System.out.println(p.toString());
    }
}
