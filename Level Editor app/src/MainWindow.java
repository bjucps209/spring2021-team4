import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.*;

// import model.Level;



// how will user begin adding elements of a level?
// click a button so that the user can create a basic entity, then change the entity using the drop down box?


// make the one combo box have everytiype of entity in the game so that the user can directyl create whatever he wants

// i dont need buttons to create every type of entity, just the one submit value button. I need to have textFields for time to appear attribute. angle?
// need to discuss with team the PNG images we will be using. 
// import the model so it will be easier to save
public class MainWindow {

    @FXML
    Pane pane;

    @FXML
    VBox vbox;

    @FXML
    Label lblLoc;

    @FXML
    Label lblHeading;

    
    @FXML
    Label lblSpeed;



    @FXML
    ComboBox<String> typeBox = new ComboBox<>();

    @FXML
    TextField txtF1;

    @FXML
    TextField txtF2;

    @FXML
    TextField txtF3;

    @FXML
    TextField txtF4;


    

    @FXML
    ImageView currentImage;

    String fileName = "customLevel";
    int numLevels;

    // Level level = new Level();
    @FXML
    void initialize() {
        String[] types = {"bold_silver", "bolt_bronze", "bolt_gold", "pill_blue", "pill_green", "pill_red", "pill_yellow", "powerupBlue_bolt", "powerupBlue_bolt"};
        typeBox.getItems().addAll(types);
        vbox.getChildren().add(typeBox);

        // lblLoc.textProperty().bind(Bindings.createStringBinding(
        //     () -> String.valueOf(currentImage.getX()),
        //     currentImage.layoutXProperty()
        // ));




    }
    
    @FXML 
    void setCurrent(ImageView img) {
        // scaleable method to set the current image for the user.
        currentImage = img;
        currentImage.getStyleClass().add("current");
    }
    

    
    @FXML
    void onCreateEnemyObjectClicked() {
        // create an obstacle on the pane, then let the user modify it by drag and drop, manually setting x and y values, selecting its type, etc.
    }

    

    @FXML
    void onUpdateValuesClicked() {
        if (currentImage != null) {
            currentImage.setX(Integer.parseInt(txtF1.getText()));
            currentImage.setY(Integer.parseInt(txtF2.getText()));
        }
        
    }

    @FXML
    void onCreateNewClicked() {
        // clicking this button will update the current image to its correct position...

        String str = (String) typeBox.getValue();

        var imgView = new ImageView("/PNG/Power-ups/" + str + ".png");
        imgView.setOnMouseDragged(this::onMouseDragged);
        imgView.setOnMouseClicked(this::onMouseClicked);



        imgView.setX(Integer.parseInt(txtF1.getText()));
        imgView.setY(Integer.parseInt(txtF2.getText()));
        pane.getChildren().add(imgView);

        

        
    }

    
    // methods for draggin and dropping the current an image, as well as selecting the current image
    @FXML
    void onMouseClicked(MouseEvent e) {
        setCurrent((ImageView) e.getSource());
        setLabels();
        
    }
    
    @FXML
    void onMouseDragged(MouseEvent e) {
        setCurrent((ImageView) e.getSource());
        currentImage.setX(e.getX());
        currentImage.setY(e.getY());
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
    void onSaveLevelClicked() throws IOException {
        String levelInfo = "";
        try (var stream = new FileOutputStream(fileName);) {

        }
        catch (IOException e) {

        }
        

    }

    
    // method to set the labels on the side of the pane
    @FXML
    void setLabels() {
        
    }
}
