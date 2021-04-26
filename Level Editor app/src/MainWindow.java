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
import model.GameObjects.Obstacles.*;

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
    ComboBox<String> typeBox = new ComboBox<>();


    @FXML
    TextField txtFXValue;

    @FXML
    TextField txtFYValue;


    @FXML
    TextField txtFFileName;
    

    @FXML
    ImageView currentImage;

    GameObject currentObject;


    
    @FXML
    void initialize() {
        String[] types = {"Player playerShip1_blue", "Square block_square", "Large block_large", "Narrow block_narrow", "Freeze powerupBlue_bolt", "HealthGainBig pill_yellow", "TemporaryInvincible shield_gold", "DestroyShip bolt_gold", "HealthGainSmall pill_blue", "Bouncer enemyBlack4", "Tracker enemyBlack1", "Ghost enemyBlack2"};
        typeBox.getItems().addAll(types);
        vbox1.getChildren().add(typeBox);
        
        pane.getStyleClass().add("backGround");
        

    }

    // factory method to create an instance of GameObject when the user clicks create new
    GameObject createGameObjects(String identifier) {
        // obstacles
        GameObject object;
        if (identifier.equals("block_square")) {
            object = Obstacle.create(ObstacleTypes.SQUARE);
        }
        else if (identifier.equals("block_large")) {
            object = Obstacle.create(ObstacleTypes.LARGE);
        }
        else if (identifier.equals("block_narrow")) {
            object = Obstacle.create(ObstacleTypes.NARROW);
        }

        // powerups
        else if (identifier.equals("powerupBlue_bolt")) {
            object = PowerUp.create(PowerUps.Freeze);
            
        }
        else if (identifier.equals("pill_yellow")) {
            object = PowerUp.create(PowerUps.HealthGainBig);
            
        }
        else if (identifier.equals("shield_gold")) {
            object = PowerUp.create(PowerUps.TemporaryInvincible);
            ;
        }
        else if (identifier.equals("bolt_gold")) {
            object = PowerUp.create(PowerUps.DestroyShip);
            
        }
        else if (identifier.equals("pill_blue")) {
            object = PowerUp.create(PowerUps.HealthGainSmall);
            
        }
        // player case
        else if (identifier.equals("playerShip1_blue")) {
            object = new Player();
        }

        // enemies
        else if (identifier.equals("enemyBlack4")) {
            object = EnemyObject.create(EnemyTypes.BOUNCER);
        }
        else if (identifier.equals("enemyBlack1")) {
            object = EnemyObject.create(EnemyTypes.TRACKER);
        }
        else {
            object = EnemyObject.create(EnemyTypes.GHOST);
        }


        return object;
    }

    // scaleable method to set the position  for an imageview and its corresponding object
    @FXML
    void setPosition(ImageView imgView, int x, int y) {

        if (x < 7) {
            x  = 7;
        }
        if (x >= (int) pane.getWidth() - 7) {
            x = (int) pane.getWidth() - 7;
        }
        if (y < 7) {
            y = 7;
        }
        if (y >= (int) pane.getHeight() - 7) {
            y = (int) pane.getHeight() - 7;
        }

        imgView.setX(x);
        imgView.setY(y);

        GameObject obj = (GameObject) imgView.getUserData();
        obj.setX(x);
        obj.setY(y);
        obj.setAppearTime(60);
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
            
            int x, y;

            if (txtFXValue.getText().equals("")) {
                x = 7;
            }
            else {
                try {
                    x = Integer.parseInt(txtFXValue.getText());
                }
                catch (NumberFormatException e) {
                    x = 7;
                }
            }
            if (txtFYValue.getText().equals("")) {
                y = 7;
            }
            else {
                try {
                    y = Integer.parseInt(txtFYValue.getText());
                }
                catch (NumberFormatException e) {
                    y = 7;
                }
            }
            setPosition(currentImage, x, y);
        }
        setLabels();
        
    }

    // create a new object
    @FXML
    void onCreateNewClicked() {
        try {
            // tab the following in if try catch re implemented
            String str = typeBox.getValue();
        
            // each option in the combobox has a user friendly word followed by the name of the png file that represents it, separated by a space. I used the png file "word" when designing the Level Builder
            String[] split = str.split(" ");
            ImageView imgView = new ImageView("/LevelEditorImages/" + split[1] + ".png");
            
            imgView.setOnMouseDragged(this::onMouseDragged);
            imgView.setOnMouseClicked(this::onMouseClicked);
            // pass in the string to be evaluated in createGameObjects
            var obj = createGameObjects(split[1]);
            imgView.setUserData(obj);
        
            int x, y;

            if (txtFXValue.getText().equals("")) {
                x = 7;
            }
            else {
                try {
                    x = Integer.parseInt(txtFXValue.getText());
                }
                catch (NumberFormatException e) {
                    x = 7;
                }
            }

            if (txtFYValue.getText().equals("")) {
                y = 7;
            }
            else {
                try {
                    y = Integer.parseInt(txtFYValue.getText());
                }
                catch (NumberFormatException e) {
                    y = 7;
                }
            }
            setPosition(imgView, x, y);
            
            pane.getChildren().add(imgView);
            setCurrent(imgView);
            setLabels();
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

        int x = (int) e.getX();
        int y = (int) e.getY();

        currentImage.setX(x);
        currentImage.setY(y);
        var obj = (GameObject) currentImage.getUserData();
        obj.setX(x);
        obj.setY(y);

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

        String allObjectInformation = "";

        for (int i = 0; i < pane.getChildren().size(); i++) {
            // get imageview to find object
            ImageView imgView = (ImageView) pane.getChildren().get(i);
            var obj = (GameObject) imgView.getUserData();
            
            String classString = String.valueOf(obj.getClass());

            String[] array = classString.split(Pattern.quote("."));
            String x = String.valueOf(obj.getX());
            String y = String.valueOf(obj.getY());
            String appearTime = String.valueOf(obj.getAppearTime());

            allObjectInformation += (array[(array.length - 1)] + "," + x + "," + y + "," + appearTime + "|");
            
        }

        levelInfo += (allObjectInformation);
        // try (var stream = new FileOutputStream(basePath + "Wave\\customLevel" + String.valueOf(fileNumber) + ".dat");)
        if (levelInfo.length() > 1) {
            if (!fileName.equals("")) {
                try (var stream = new FileOutputStream(basePath + "Wave\\" + fileName + ".dat");) {
                    byte[] byteLevelInfo = levelInfo.getBytes();
                    stream.write(byteLevelInfo);
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
        }
        else {
            lblLoc.setText("");
        }
    }
}