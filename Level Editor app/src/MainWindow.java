import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.*;

import model.Level;
import model.GameObjects.*;
import model.Enums.*;

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
    TextField txtFXValue;

    @FXML
    TextField txtFYValue;

    @FXML
    TextField txtFHeading;

    @FXML
    TextField txtFAppearanceTime;


    

    @FXML
    ImageView currentImage;

    GameObject currentObject;

    String fileName = "customLevel";
    int numLevels;

    
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

    // factory method to create an instance of GameObject when the user clicks create new
    GameObject createGameObjects(String identifier) {
        switch (identifier) {
            case "bold_silver":
                return new Obstacle();
            case "powerupBlue_bolt":
                return new Obstacle();
            default:
                return new Obstacle();

        }
    }
    
    @FXML 
    void setCurrent(ImageView img) {
        // scaleable method to set the current image for the user.
        currentImage = img;
        currentImage.getStyleClass().add("current");
        try {
            currentObject = (GameObject) currentImage.getUserData();
        }
        catch (Exception e) {
            System.out.println("failed");
        }
    }


    @FXML
    void onUpdateValuesClicked() {
        if (currentImage != null && currentObject != null) {
            currentImage.setX(Integer.parseInt(txtFXValue.getText()));
            currentImage.setY(Integer.parseInt(txtFYValue.getText()));

            currentObject.setX(Integer.parseInt(txtFXValue.getText()));
            currentObject.setY(Integer.parseInt(txtFYValue.getText()));
        }
        
    }

    @FXML
    void onCreateNewClicked() {
        // clicking this button will update the current image to its correct position...

        String str = (String) typeBox.getValue();

        var imgView = new ImageView("/PNG/Power-ups/" + str + ".png");
        imgView.setOnMouseDragged(this::onMouseDragged);
        imgView.setOnMouseClicked(this::onMouseClicked);

        var obj = createGameObjects(str);
        imgView.setUserData(obj);



        imgView.setX(Integer.parseInt(txtFXValue.getText()));
        imgView.setY(Integer.parseInt(txtFYValue.getText()));

        obj.setX(Integer.parseInt(txtFXValue.getText()));
        obj.setY(Integer.parseInt(txtFYValue.getText()));

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
        currentImage.setX((int) e.getX());
        currentImage.setY((int) e.getY());

        currentObject.setX((int) e.getX());
        currentObject.setY((int) e.getY());
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
        if (currentImage != null) {
            lblLoc.setText("(" + String.valueOf(currentImage.getX()) + "," + String.valueOf(currentImage.getY()) + ")");
            lblHeading.setText("heading here");
            lblSpeed.setText("speed here");
        }
        else {
            lblLoc.setText("");
            lblHeading.setText("");
            lblSpeed.setText("");
        }
    }
}
