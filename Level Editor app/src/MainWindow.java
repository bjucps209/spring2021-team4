import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.regex.Pattern;

import model.Enums.*;
import model.GameObjects.*;
import model.GameObjects.Enemies.*;
import model.GameObjects.Powerups.*;


// re implement createNewObject
// uncomment certain elements of save level when safe
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

    

    @FXML
    ImageView currentImage;

    GameObject currentObject;

    int fileNumber = 0;


    
    @FXML
    void initialize() {
        String[] types = {"block_square", "block_corner", "block_large", "block_narrow", "powerupBlue_bolt", "pill_yellow", "shield_gold", "bolt_gold", "pill_blue", "enemyBlack4", "enemyBlack1", "enemyBlack2", "laser(uninmplemented)"};
        typeBox.getItems().addAll(types);
        vbox.getChildren().add(typeBox);

        

    }

    // factory method to create an instance of GameObject when the user clicks create new
    GameObject createGameObjects(String identifier) {
        // // obstacles
        if (identifier.equals("block_square") || identifier.equals("block_corner") || identifier.equals("block_large") || identifier.equals("block_narrow")) {
            return new Obstacle();
        }
        // // powerups
        // else if (identifier.equals("powerupBlue_bolt")) {
        //     var freeze = new FreezePowerUp();
        //     try {
        //         freeze.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
        //     }
        //     catch (NumberFormatException e) {
        //         freeze.setAppearTime(0);
        //     }
        //     return freeze;
        // }
        // else if (identifier.equals("pill_yellow")) {
        //     var largeHealth = new LargeHealthPowerUp();
        //     try {
        //         largeHealth.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
        //     }
        //     catch (NumberFormatException e) {
        //         largeHealth.setAppearTime(0);
        //     }
        //     return largeHealth;
        // }
        // else if (identifier.equals("shield_gold")) {
        //     var invincibility = new InvincibilityPowerUp();
        //     try {
        //         invincibility.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
        //     }
        //     catch (NumberFormatException e) {
        //         invincibility.setAppearTime(0);
        //     }
        //     return invincibility;
        // }
        // else if (identifier.equals("bolt_gold")) {
        //     var destroyShip = new DestroyShipPowerUp();
        //     try {
        //         destroyShip.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
        //     }
        //     catch (NumberFormatException e) {
        //         destroyShip.setAppearTime(0);
        //     }
        //     return destroyShip;
        // }
        // else if (identifier.equals("pill_blue")) {
        //     var healthPack = new HealthPackPowerUp();
        //     try {
        //         healthPack.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
        //     }
        //     catch (NumberFormatException e) {
        //         healthPack.setAppearTime(0);
        //     }
        //     return healthPack;
        // }

        // enemies
        // shapeshifter not implemented
        else if (identifier.equals("enemyBlack4")) {
            return EnemyObject.create(EnemyTypes.BOUNCER);
        }
        else if (identifier.equals("enemyBlack1")) {
            return EnemyObject.create(EnemyTypes.TRACKER);
        }
        else if (identifier.equals("enemyBlack2")) {
            return EnemyObject.create(EnemyTypes.GHOST);
        }
        else {
            return EnemyObject.create(EnemyTypes.LASER);
        }
        
    }

    // scaleable method to set the position  for an imageview and its corresponding object
    @FXML
    void setPosition(ImageView imgView, int x, int y) {
        imgView.setX(x);
        imgView.setY(y);

        GameObject obj = (GameObject) imgView.getUserData();

        obj.setX(x);
        obj.setY(y);
        }

    // scaleable method to set the current image for the user.
    @FXML 
    void setCurrent(ImageView img) {
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

    // change applicable attributes of the current image
    @FXML
    void onUpdateValuesClicked() {
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
            // if (currentImage.getUserData() instanceof PowerUp) {
            //     var powerUp = (PowerUp) currentImage.getUserData();
            //     try {
            //         powerUp.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
            //     }
            //     catch (NumberFormatException f) {
            //         var alert = new Alert(AlertType.INFORMATION, "Please supply Integer values only.");
            //         alert.show();
            //     }
            // }
        }
        setLabels();
        
    }

    // create a new object
    @FXML
    void onCreateNewClicked() {
        String str = (String) typeBox.getValue();
        var imgView = new ImageView("/LevelEditorImages/" + str + ".png");

        imgView.setOnMouseDragged(this::onMouseDragged);
        imgView.setOnMouseClicked(this::onMouseClicked);

        var obj = createGameObjects(str);
        imgView.setUserData(obj);
        
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

    // remove entities from the level
    @FXML
    void onClearClicked() {
        pane.getChildren().remove(0, pane.getChildren().size());
        setLabels();
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


    // stringify, 'bytify', and write to a file.
    @FXML
    void onSaveLevelClicked() throws IOException {
        String levelInfo = "";

        String allObjectInformation = "";

        for (int i = 0; i < pane.getChildren().size(); i++) {
            // get imageview to find object
            ImageView imgView = (ImageView) pane.getChildren().get(i);
            var obj = (GameObject) imgView.getUserData();
            
            String classString = String.valueOf(obj.getClass());

            String[] array = classString.split(Pattern.quote("."));
            String x = String.valueOf(obj.getX());
            String y = String.valueOf(obj.getY());

            allObjectInformation += ("|" + array[2] + "," + x + "," + y);
            if (obj instanceof PowerUp) {
                var downCastedObj = (PowerUp) obj;
                // allObjectInformation += ("," + downCastedObj.getAppearTime());
            }
            if (i == pane.getChildren().size() - 1) {
                allObjectInformation += "|";
            }
            
        }
        if (String.valueOf(allObjectInformation.length()).length() < 4) {
            String str = "";
            for (int i = 0; i < (4 -String.valueOf(allObjectInformation.length()).length()); i++) {
                str += "0";
            }
            str += String.valueOf(allObjectInformation.length());
            allObjectInformation = str + allObjectInformation;
        }

        levelInfo += (allObjectInformation);

        if (levelInfo.length() > 4) {
            try (var stream = new FileOutputStream("customLevel" + String.valueOf(fileNumber) + ".dat");) {
                
                byte[] byteLevelInfo = levelInfo.getBytes();
                stream.write(byteLevelInfo);
                fileNumber ++;
                if (fileNumber == 10) {
                    var alert = new Alert(AlertType.INFORMATION, "10 levels created.");
                    alert.show();
                }
                
            }
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
                // lblTime.setText(String.valueOf(obj.getAppearTime()));
            }
        }
        else {
            lblLoc.setText("");
            lblHeading.setText("");
            lblTime.setText("");
        }
    }
}
