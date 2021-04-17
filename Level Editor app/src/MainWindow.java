import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
// import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.*;
// import java.net.FileNameMap;
// import java.nio.ByteBuffer;
import java.util.regex.Pattern;

import model.Enums.*;
import model.GameObjects.*;
import model.GameObjects.Enemies.*;
import model.GameObjects.Powerups.*;

// import model.Level;


// when saving a laser, add in H or V for Horizontal/ vertical 
public class MainWindow {

    @FXML
    Pane pane;

    @FXML
    VBox vbox1;
    @FXML
    VBox vbox2;
    @FXML
    VBox saveFileVBox;

    @FXML
    Label lblLoc;

    @FXML
    Label lblHeading;

    
    @FXML
    Label lblTime;



    @FXML
    ComboBox<String> typeBox = new ComboBox<>();
    @FXML
    ComboBox<String> orientationBox = new ComboBox<>();

    @FXML
    TextField txtFXValue;

    @FXML
    TextField txtFYValue;

    @FXML
    TextField txtFHeading;

    @FXML
    TextField txtFAppearanceTime;

    @FXML
    TextField txtFFileName;
    

    @FXML
    ImageView currentImage;

    GameObject currentObject;

    int fileNumber = 0;

    String difficulty = "E";


    
    @FXML
    void initialize() {
        String[] types = {"Player playerShip1_blue", "Obstacle block_square", "Obstacle block_corner", "Obstacle block_large", "Obstacle block_narrow", "Freeze powerupBlue_bolt", "HealthGainBig pill_yellow", "TemporaryInvincible shield_gold", "DestroyShip bolt_gold", "HealthGainSmall pill_blue", "Bouncer enemyBlack4", "Tracker enemyBlack1", "Ghost enemyBlack2", "Laser cockpitGreen_0", "Shapeshifter enemyBlack4"};
        typeBox.getItems().addAll(types);
        vbox1.getChildren().add(typeBox);

        String[] orientations = {"Horizontal", "Vertical"};
        orientationBox.getItems().addAll(orientations);
        vbox2.getChildren().add(orientationBox);
        pane.getStyleClass().add("backGround");
        

    }

    // factory method to create an instance of GameObject when the user clicks create new
    GameObject createGameObjects(String identifier) {
        if (identifier.equals("block_square") || identifier.equals("block_corner") || identifier.equals("block_large") || identifier.equals("block_narrow")) {
            return new Obstacle();
        }
        else if (identifier.equals("powerupBlue_bolt")) {
            var freeze = PowerUp.create(PowerUps.Freeze);
            try {
                freeze.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
            }
            catch (NumberFormatException e) {
                freeze.setAppearTime(0);
            }
            return freeze;
        }
        else if (identifier.equals("pill_yellow")) {
            var largeHealth = PowerUp.create(PowerUps.HealthGainBig);
            try {
                largeHealth.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
            }
            catch (NumberFormatException e) {
                largeHealth.setAppearTime(0);
            }

            return largeHealth;
        }
        else if (identifier.equals("shield_gold")) {
            var invincibility = PowerUp.create(PowerUps.TemporaryInvincible);
            try {
                invincibility.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
            }
            catch (NumberFormatException e) {
                invincibility.setAppearTime(0);
            }
            return invincibility;
        }
        else if (identifier.equals("bolt_gold")) {
            var destroyShip = PowerUp.create(PowerUps.DestroyShip);
            try {
                destroyShip.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
            }
            catch (NumberFormatException e) {
                destroyShip.setAppearTime(0);
            }
            return destroyShip;
        }
        else if (identifier.equals("pill_blue")) {
            var healthPack = PowerUp.create(PowerUps.HealthGainSmall);
            try {
                healthPack.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
            }
            catch (NumberFormatException e) {
                healthPack.setAppearTime(0);
            }
            return healthPack;
        }
        // player case
        else if (identifier.equals("playerShip1_blue")) {
            return new Player();
        }

        // enemies
        else if (identifier.equals("cockpitGreen_0")) {
            return EnemyObject.create(EnemyTypes.LASER);
        }
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
            return EnemyObject.create(EnemyTypes.SHAPESHIFTER);
        }
        
    }

    // scaleable method to set the position  for an imageview and its corresponding object
    @FXML
    void setPosition(ImageView imgView, int x, int y) {

        if (x < 0) {
            x  = 0;
        }
        if (x >= (int) pane.getWidth()) {
            x = (int) pane.getWidth();
        }
        if (y < 0) {
            y = 0;
        }
        if (y >= (int) pane.getHeight()) {
            y = (int) pane.getHeight();
        }

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
                    var alert = new Alert(AlertType.ERROR, "Please supply Integer values only.");
                    alert.show();
                }
            }
            if (currentImage.getUserData() instanceof PowerUp) {
                var powerUp = (PowerUp) currentImage.getUserData();
                try {
                    powerUp.setAppearTime(Integer.parseInt(txtFAppearanceTime.getText()));
                }
                catch (NumberFormatException f) {
                    var alert = new Alert(AlertType.ERROR, "Please supply Integer values only.");
                    alert.show();
                }
            }
        }
        setLabels();
        
    }

    // methods to set the level difficulty
    @FXML
    void onEasyClicked() {
        difficulty = "E";
    }

    @FXML
    void onMediumClicked() {
        difficulty = "M";
    }

    @FXML
    void onHardClicked() {
        difficulty = "H";
    }
    // ----

    // create a new object
    @FXML
    void onCreateNewClicked() {
        try {
            // tab the following in if try catch re implemented
            String str = (String) typeBox.getValue();
        
            // each option in the combobox has a user friendly word followed by the name of the png file that represents it, separated by a space. I used the png file "word" when designing the Level Builder
            String[] split = str.split(" ");
            ImageView imgView = new ImageView("/LevelEditorImages/" + split[1] + ".png");
            
            imgView.setOnMouseDragged(this::onMouseDragged);
            imgView.setOnMouseClicked(this::onMouseClicked);
            // pass in the string to be evaluated in createGameObjects
            var obj = createGameObjects(split[1]);
            imgView.setUserData(obj);
            
            if (split[0].equals("Laser")) {
                try {
                    if (orientationBox.getValue().equals("Horizontal")) {
                        setPosition(imgView, 0, Integer.parseInt(txtFYValue.getText()));
                    }
                    else if (orientationBox.getValue().equals("Vertical")) {
                        setPosition(imgView, Integer.parseInt(txtFXValue.getText()), 0);
                    }
                    pane.getChildren().add(imgView);
                }
                catch (NullPointerException e) {
                    var alert = new Alert(AlertType.ERROR, "Please select an orientation for the laser.");
                    alert.show();
                }
            }
            else {
                if (txtFXValue.getText().equals("") && txtFYValue.getText().equals("")) {
                    pane.getChildren().add(imgView);
                    setPosition(imgView, 0, 0);
                }
                else {
                    try {
                        pane.getChildren().add(imgView);

                        setPosition(imgView, Integer.parseInt(txtFXValue.getText()), Integer.parseInt(txtFYValue.getText()));
                    }
                    catch (NumberFormatException e) {
                        var alert = new Alert(AlertType.ERROR, "Please choose an integer.");
                        alert.show();
                    }
                }
            }
            setCurrent(imgView);
        }
        catch (IllegalArgumentException e) {
            var alert = new Alert(AlertType.ERROR, e.getMessage());
            alert.show();
        }
        
    }

    // remove entities from the level
    @FXML
    void onClearClicked() {
        pane.getChildren().remove(0, pane.getChildren().size());
        setLabels();
        difficulty = "E";
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


    // stringify, 'bytify', and write to a file in Wave.
    @FXML
    void onSaveLevelClicked() throws IOException {
        // make this file and get its absolute path so creating files in other folders works on any system.
        File f = new File("MainWindow.java");

        String fileName = txtFFileName.getText();

        String absolutePath = f.getAbsolutePath();
        int findWave = absolutePath.indexOf("Level Editor app");
        String basePath = absolutePath.substring(0, findWave);

        String levelInfo = "";

        String allObjectInformation = difficulty;

        for (int i = 0; i < pane.getChildren().size(); i++) {
            // get imageview to find object
            ImageView imgView = (ImageView) pane.getChildren().get(i);
            var obj = (GameObject) imgView.getUserData();
            
            String classString = String.valueOf(obj.getClass());

            String[] array = classString.split(Pattern.quote("."));
            String x = String.valueOf(obj.getX());
            String y = String.valueOf(obj.getY());

            allObjectInformation += (array[(array.length - 1)] + "," + x + "," + y);
            if (obj instanceof PowerUp) {
                var downCastedObj = (PowerUp) obj;
                allObjectInformation += ("," + downCastedObj.getAppearTime());
            }
            allObjectInformation += "|";
            // if lasers have an orientation attribute
            // if (obj instanceof Laser) {
            //     var downCastedObj = (Laser) obj;
            //     allObjectInformation += ("," + downCastedObj.getOrientation());
            // }
            
        }

        levelInfo += (allObjectInformation);
        // try (var stream = new FileOutputStream(basePath + "Wave\\customLevel" + String.valueOf(fileNumber) + ".dat");)
        if (levelInfo.length() > 0) {
            if (!fileName.equals("")) {
                try (var stream = new FileOutputStream(basePath + "Wave\\" + fileName + ".dat");) {
                    byte[] byteLevelInfo = levelInfo.getBytes();
                    stream.write(byteLevelInfo);
                    var alert = new Alert(AlertType.INFORMATION, "File saved as " + fileName);
                    alert.show();
                }
            }
            else {
                var alert = new Alert(AlertType.ERROR, "Please pick a file name.");
                alert.show();
            }
        }
        else {
            var alert = new Alert(AlertType.ERROR, "Cannot save an 'empty' level.");
            alert.show();
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