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
    Label lblId;

    @FXML
    Label lblLoc;

    @FXML
    Label lblHeading;

    @FXML
    Label lblState;

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

    Level level;
    
    @FXML
    void initialize() {
        
    }
    
    
    // creates am instance of wanderer and a representing ImageView
    @FXML
    void onCreateWandererClicked() {
        
    }

    // creates an instance of tracker and a representing ImageView
    @FXML
    void onCreateTrackerClicked() {
        
    }

    // removes the current image from the pane, removes the critter from the world list of critters, sets the current critter to null, and updates the labels
    @FXML
    void onDeleteClicked() {
        
    }
    // gets the last command added to the undo list and undoes it
    @FXML
    void onUndoClicked() {
        
        
    }

    // what happens when the user clicks on an actual image to select it
    @FXML
    void onImageClicked(MouseEvent e) {
        setLabels();
        
    }

    // sets the current image and critter so onMouseReleased can work with them
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
