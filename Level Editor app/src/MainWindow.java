import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.*;

import model.Level;
import model.GameObjects.*;
import model.Enums.*;

// when the user selects something representing a powerup, create an instance of powerup.
// when the user creates an obstacle, create an instance of obstacle.
// when the user creates an entity, create an instance of entity class.
// need these classes created so serialization works

// half methods : (these methods need more model classes to be completely implemented)
// onSaveLevelClicked()
// getObjectClass()
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
    Label lblTime;



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

    // not sure if i need a level yet
    // Level level = new Level();


    

    @FXML
    ImageView currentImage;

    GameObject currentObject;

    String fileName = "customLevel.dat";

    // optional value to store how many levels will be written to the custom level file -> not sure if needed yet
    // int numLevels;

    
    @FXML
    void initialize() {
        String[] types = {"block_square", "block_corner", "block_large", "block_narrow", "powerupBlue_bolt", "pill_yellow", "shield_gold", "bolt_gold", "pill_blue", "enemyBlack4", "enemyBlack1", "enemyBlack2", "laser(uninmplemented)"};
        typeBox.getItems().addAll(types);
        vbox.getChildren().add(typeBox);

        

    }

    // factory method to create an instance of GameObject when the user clicks create new
    GameObject createGameObjects(String identifier) {
        // obstacles
        if (identifier.equals("block_square") || identifier.equals("block_corner") || identifier.equals("block_large") || identifier.equals("block_narrow")) {
            return new Obstacle();
        }
        // powerups
        else if (identifier.equals("powerupBlue_bolt")) {
            var freeze = new FreezePowerUp();
            try {
                freeze.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
            }
            catch (NumberFormatException e) {
                freeze.setAppearTime(0);
            }
            return freeze;
        }
        else if (identifier.equals("pill_yellow")) {
            var largeHealth = new LargeHealthPowerUp();
            try {
                largeHealth.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
            }
            catch (NumberFormatException e) {
                largeHealth.setAppearTime(0);
            }
            return largeHealth;
        }
        else if (identifier.equals("shield_gold")) {
            var invincibility = new InvincibilityPowerUp();
            try {
                invincibility.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
            }
            catch (NumberFormatException e) {
                invincibility.setAppearTime(0);
            }
            return invincibility;
        }
        else if (identifier.equals("bolt_gold")) {
            var destroyShip = new DestroyShipPowerUp();
            try {
                destroyShip.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
            }
            catch (NumberFormatException e) {
                destroyShip.setAppearTime(0);
            }
            return destroyShip;
        }
        else if (identifier.equals("pill_blue")) {
            var healthPack = new HealthPackPowerUp();
            try {
                healthPack.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
            }
            catch (NumberFormatException e) {
                healthPack.setAppearTime(0);
            }
            return healthPack;
        }
        // enemies
        else if (identifier.equals("enemyBlack4")) {
            return new Bouncer();
        }
        else if (identifier.equals("enemyBlack1")) {
            return new Tracker();
        }
        else if (identifier.equals("enemyBlack2")) {
            return new Ghost();
        }
        else {
            return new Laser();
        }
        
    }

    // use this method to try to get the exact class of an object. usefull when extracting user data from an ImageView
    GameObject getObjectClass(Object obj) {
        try {
            Obstacle obstacle = (Obstacle) obj;
            return obstacle;
        }
        catch (ClassCastException e) {
            // keep nesting try catch statements to try casting to every object until the correct type is found
            return null;
            // try {

            // }
        }

    }
    

    @FXML
    void setPosition(ImageView imgView, int x, int y) {
        // scaleable method to set the position  for an imageview and its corresponding object
        imgView.setX(x);
        imgView.setY(y);

        GameObject obj = (GameObject) imgView.getUserData();

        obj.setX(x);
        obj.setY(y);
        }


    @FXML 
    void setCurrent(ImageView img) {
        // scaleable method to set the current image for the user.
        if (currentImage != null) {
            currentImage.getStyleClass().remove("current");
        }
        currentImage = img;
        currentImage.getStyleClass().add("current");
        try {
            currentObject = (GameObject) currentImage.getUserData();
        }
        catch (Exception e) {
            
        }
    }


    @FXML
    void onUpdateValuesClicked() {
        // change the position of the entity to a specific value
        if (currentImage != null && currentObject != null) {
            if (txtFXValue.getText().equals("") && txtFYValue.getText().equals("")) {
                setPosition(currentImage, 0, 0);
            }
            else {
                try {
                    setPosition(currentImage, Integer.parseInt(txtFXValue.getText()), Integer.parseInt(txtFYValue.getText()));
                }
                catch (NumberFormatException e) {
                    var alert = new Alert(AlertType.INFORMATION, "Please supply Integer values only.");
                    alert.show();
                }
            }
            if (currentImage.getUserData() instanceof PowerUp) {
                var powerUp = (PowerUp) currentImage.getUserData();
                try {
                    powerUp.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
                }
                catch (NumberFormatException f) {
                    var alert = new Alert(AlertType.INFORMATION, "Please supply Integer values only.");
                    alert.show();
                }
            }
        }
        setLabels();
        
    }

    @FXML
    void onCreateNewClicked() {
        // clicking this button will update the current image to its correct position...
        String str = (String) typeBox.getValue();
        var imgView = new ImageView("/LevelEditorImages/" + str + ".png");

        imgView.setOnMouseDragged(this::onMouseDragged);
        imgView.setOnMouseClicked(this::onMouseClicked);

        var obj = createGameObjects(str);
        imgView.setUserData(obj);
        // level.getAllObjects().add(obj);
        
        if (txtFXValue.getText().equals("") && txtFYValue.getText().equals("")) {
            try {
                pane.getChildren().add(imgView);
                setPosition(imgView, 0, 0);
            }
            catch (NumberFormatException e) {
                var alert = new Alert(AlertType.INFORMATION, "Please choose an integer.");
                alert.show();
            }
        }
        else {
            try {
                pane.getChildren().add(imgView);
                setPosition(imgView, Integer.parseInt(txtFXValue.getText()), Integer.parseInt(txtFYValue.getText()));
            }
            catch (NumberFormatException e) {
                var alert = new Alert(AlertType.INFORMATION, "Please choose an integer.");
                alert.show();
            }
        }
    }

    
    // method to select an image
    @FXML
    void onMouseClicked(MouseEvent e) {
        setCurrent((ImageView) e.getSource());
        setLabels();
        
    }
    

    // method to drag an image
    @FXML
    void onMouseDragged(MouseEvent e) {
        setCurrent((ImageView) e.getSource());

        setPosition(currentImage, (int) e.getX(), (int) e.getY());

        setLabels();
    }


    // stringify and write to a file.
    @FXML
    void onSaveLevelClicked() throws IOException {
        String levelLength = "";

        String allObjectInformation = "";

        for (int i = 0; i < pane.getChildren().size(); i++) {
            ImageView imgView = (ImageView) pane.getChildren().get(i);
            GameObject obj = (GameObject) imgView.getUserData();
            String x = String.valueOf(obj.getX());
            String y = String.valueOf(obj.getY());
            allObjectInformation += ("|GAMEOBJECT," + x + "," + y);
            if (i == pane.getChildren().size() - 1) {
                allObjectInformation += "|";
            }
            
        }
        

        // work on formatting the 4 digit length string if the length of the string version of the length of allObjectInformation is less than 4, keep adding zeros in front of the numbers till the length equals 4
        if (String.valueOf(allObjectInformation.length()).length() < 4) {
            String str = "";
            System.out.println("less than 4");
            System.out.println(allObjectInformation.length());
            while (str.length() + allObjectInformation.length() < 4) {
                str += "0";
                System.out.println("while loop");
            }
            str += String.valueOf(allObjectInformation.length());
            allObjectInformation = str + allObjectInformation;
            // allObjectInformation = (str + String.valueOf(allObjectInformation.length()));

        }
        System.out.println(allObjectInformation.length());
        levelLength += (allObjectInformation);
        System.out.println(levelLength);

        // potentially remove ##LEVELSTART and ##LEVELEND
        // have a string of only object, info, delimited by |


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
            if (currentImage.getUserData() instanceof PowerUp) {
                var obj = (PowerUp) currentImage.getUserData();
                lblTime.setText(String.valueOf(obj.getAppearTime()));
            }
        }
        else {
            lblLoc.setText("");
            lblHeading.setText("");
            lblTime.setText("");
        }
    }
}
