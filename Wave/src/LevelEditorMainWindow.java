import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import model.Level;


public class LevelEditorMainWindow {

    @FXML
    Pane pane;

    @FXML
    Label lblLoc;

    @FXML
    Label lblHeading;


    @FXML
    Label lblSpeed;

    @FXML
    TextField txtF1;

    @FXML
    TextField txtF2;

    @FXML
    TextField txtF3;



    @FXML
    ImageView currentImage;

    Level level = new Level();
    
    
    
    
    @FXML
    void onCreateObstacleClicked() {
        
    }

    
    @FXML
    void onCreateEnemyObjectClicked() {
        
    }

    @FXML 
    void onCreatePowerupClicked() {

    }

    @FXML
    void onSubmitValuesClicked() {
        
    }

    
    @FXML
    void onImageClicked(MouseEvent e) {
        setLabels();
        
    }

    
    @FXML
    void onMousePressed(MouseEvent e) {
        
    }

    // when the mouse is released, change the image and critter's x and y values accordingly, keeping the image inside the pane
    @FXML
    void onMouseReleased(MouseEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();
        // if (x >= 775) {
        //     x = 775;
        // }
        // if (x <= 25) {
        //     x = 25;
        // }
        // if (y >= 475) {
        //     y = 475;
        // }
        // if (y <= 25) {
        //     y = 25;
        // }
        
        
        setLabels();
    }

    
    // method to set the labels on the side of the pane
    @FXML
    void setLabels() {
        
    }
}
