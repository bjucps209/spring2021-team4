import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

// import model.Level;



// how will user begin adding elements of a level?
// click a button so that the user can create a basic entity, then change the entity using the drop down box?


// make the one combo box have everytiype of entity in the game so that the user can directyl create whatever he wants 
public class MainWindow {

    @FXML
    Pane pane;

    @FXML
    Label lblLoc;

    @FXML
    Label lblHeading;


    @FXML
    Label lblSpeed;

    @FXML
    ComboBox<String> typeBox;

    @FXML
    TextField txtF2;

    @FXML
    TextField txtF3;


    

    @FXML
    ImageView currentImage;

    // Level level = new Level();
    
    @FXML 
    void setCurrent() {
        // scaleable method to set the current image for the user.

    }
    
    
    @FXML
    void onCreateObstacleClicked() {
        // create an obstacle on the pane, then let the user modify it by drag and drop, manually setting x and y values, selecting its type, etc.

    }

    
    @FXML
    void onCreateEnemyObjectClicked() {
        // create an obstacle on the pane, then let the user modify it by drag and drop, manually setting x and y values, selecting its type, etc.
    }

    @FXML 
    void onCreatePowerupClicked() {
        // create an obstacle on the pane, then let the user modify it by drag and drop, manually setting x and y values, selecting when it should appear, selecting its type, etc.
    }

    @FXML
    void onSubmitValuesClicked() {
        // clicking this button will update the current image to its correct position...
    }

    
    // methods for draggin and dropping the current an image, as well as selecting the current image
    @FXML
    void onImageClicked(MouseEvent e) {
        setLabels();
        
    }
    
    @FXML
    void onMousePressed(MouseEvent e) {
        
    }
    
    @FXML
    void onMouseReleased(MouseEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();
        
        setLabels();
    }
    


    // stringify and write to a file.
    @FXML
    void onSaveLevelClicked() {

    }

    
    // method to set the labels on the side of the pane
    @FXML
    void setLabels() {
        
    }
}
