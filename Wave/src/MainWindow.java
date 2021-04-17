import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.HighScore;
import model.HighScoreList;
import model.Level;
import model.Wave;

public class MainWindow {
    Wave w;

    HighScore player1 = new HighScore("Bob", 100);
    HighScore player2 = new HighScore("Amanda", 300);
    HighScore player3 = new HighScore("Tim", 200);
    HighScoreList highScoreList = new HighScoreList(new ArrayList<HighScore>());

    // list to be given to game if the user wants to play their custom games
    ArrayList<Level> customGameLevels = new ArrayList<>();

    // Difficulty Buttons
    Button btnEasy = new Button();
    Button btnMedium = new Button();
    Button btnHard = new Button();
    Button btnCheat = new Button();

    @FXML
    public void initialize() {
        w = Wave.getInstance();
    }

    @FXML
    public void onNewGameClicked() throws IOException {
        // // Opens window to allow player to enter their name
        // VBox vboxName = new VBox();
        // vboxName.setId("menu-background");
        // vboxName.setPadding(new Insets(10));
        // vboxName.setSpacing(10);
        // vboxName.setAlignment(Pos.CENTER);
        
        // Scene nameScene = new Scene(vboxName, 800, 600);
        // Stage nameStage = new Stage();
        // nameStage.setScene(nameScene); // set the scene
        // nameStage.setTitle("Name Menu");
        // nameStage.setAlwaysOnTop(true);

        // nameScene.getStylesheets().add("GameWindow.css");

        // TextField nameField = new TextField();
        //     Label lblName = new Label();
        //     lblName.setText("Enter Your Name:");
        //     vboxName.getChildren().add(lblName);
        //     vboxName.getChildren().add(nameField);
        //     nameField.requestFocus();
        //     nameScene.setOnKeyPressed(key -> {
        //         KeyCode keyCode = key.getCode();
        //         if (keyCode.equals(KeyCode.ENTER)) {
        //             highScoreList.getList().add(new HighScore(nameField.getText(), game.getScoreValue()));
        //             highScoreList.save();
        //         }
        //     });


        // opens up new window which is GameWindow
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        Scene scene = new Scene(loader.load());

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                if (k.getEventType() == KeyEvent.KEY_PRESSED) {
                    switch (k.getCode()) {
                    case UP:
                        GameWindow.moveUp();
                        break;
                    case DOWN:
                        GameWindow.moveDown();
                        break;
                    case LEFT:
                        GameWindow.moveLeft();
                        break;
                    case RIGHT:
                        GameWindow.moveRight();
                        break;
                    default:
                        break;
                    }
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                GameWindow.moveNeutral();
            }
        });
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Wave");
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                w.onClosed();
                GameWindow.onClosed();
            }
        });

    }

    @FXML
    // click on load level to open a screen that will allow the user to search the directory for a file and if it exists, load that file
    void onLoadCustomGameClicked() {

        VBox topVBox = new VBox();
        topVBox.setId("menu-background");
        topVBox.setAlignment(Pos.CENTER);
        topVBox.setSpacing(10);

        Label lbl = new Label("Please enter the name of the file you'd like to load.(don't worry about the .dat)");
        topVBox.getChildren().add(lbl);
        TextField txtFieldFileChooser = new TextField();
        txtFieldFileChooser.setId("TEXTFIELD");
        txtFieldFileChooser.setMaxWidth(125);
        topVBox.getChildren().add(txtFieldFileChooser);

        Button button = new Button("Add Level");
        button.setOnAction(this::callLoadCustomLevel);

        topVBox.getChildren().add(button);


        Button startGameButton = new Button("Start Custom Game");
        startGameButton.setOnAction(this::startCustomGame);
        topVBox.getChildren().add(startGameButton);

        Scene loadLevelScene = new Scene(topVBox, 800, 600);
        Stage loadLevelStage = new Stage();
        loadLevelStage.setScene(loadLevelScene);
        loadLevelStage.setTitle("Level Menu");
        loadLevelStage.show();

        loadLevelScene.getStylesheets().add("MainWindow.css");

    }

    // method that calls loadCustomLevel(String levelName) in Wave.java
    @FXML
    void callLoadCustomLevel(ActionEvent e) {
        Button button = (Button) e.getSource();
        Scene scene = button.getScene();
        TextField txtFileChoice = (TextField) scene.lookup("#TEXTFIELD");
        String fileName = txtFileChoice.getText();
        if (w.searchDirectoryForFile(fileName)) {
            try {
                Level l = w.loadCustomLevel(fileName);
                if (l != null) {
                    customGameLevels.add(l);
                }

            } catch (IOException exception) {
            }
        txtFileChoice.requestFocus();
        }
        else {
            var alert = new Alert(AlertType.INFORMATION, "That file is not in the directory.");
            alert.show();
        }
    }

    @FXML
    void startCustomGame(ActionEvent e) {
        
        if (customGameLevels.size() > 0) {
            System.out.println("start the custom game");
            // try {

            //     // w.gameStart();
            //     // onNewGameClicked();
            //     // w.getGame().setLevels(customGameLevels);
            // } catch (IOException exception) {

            // }
        } else {
            var alert = new Alert(AlertType.ERROR, "You haven't picked any custom levels to play yet.");
            alert.show();
        }

    }

    @FXML
    // Screen to show how to play the game
    public void onAboutClicked() {
        VBox vbox = new VBox();
        vbox.setId("menu-background");
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.TOP_CENTER);

        Scene aboutScene = new Scene(vbox, 800, 600);
        Stage aboutStage = new Stage();
        aboutStage.setScene(aboutScene); // set the scene
        aboutStage.setTitle("About");
        aboutStage.show();

        aboutScene.getStylesheets().add("MainWindow.css");

        String INFO = "You can begin the game by clicking on NEW GAME.\n" + "\n"
                + "When playing the game keep this in mind:\n" + "- You will lose health if you are hit by an enemy.\n"
                + "- You can run into powerups that will give you an advantage.\n";

        Label lblInfo = new Label(INFO);
        vbox.getChildren().add(lblInfo);
    }

    // screen to show the controls of the game
    public void onHelpClicked() {
        VBox vbox = new VBox();
        vbox.setId("menu-background");
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.TOP_CENTER);

        Scene helpScene = new Scene(vbox, 800, 600);
        Stage helpStage = new Stage();
        helpStage.setScene(helpScene); // set the scene
        helpStage.setTitle("Help");
        helpStage.show();

        helpScene.getStylesheets().add("MainWindow.css");

        Label lblTitle = new Label();
        lblTitle.setText("Controls");
        Label space = new Label();
        space.setText(" ");
        Label lblPlayer = new Label();
        lblPlayer
                .setText("Move Left and Right: left arrow / right arrow\n" + "Move up and down: up arrow / down arrow");

        vbox.getChildren().add(lblTitle);
        vbox.getChildren().add(space);
        vbox.getChildren().add(lblPlayer);
    }

    @FXML
    // Options for game difficulty
    public void onOptionsClicked() {
        VBox vbox = new VBox();
        vbox.setId("menu-background");
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        Scene optionScene = new Scene(vbox, 800, 600);
        Stage optionStage = new Stage();
        optionStage.setScene(optionScene); // set the scene
        optionStage.setTitle("Options");
        optionStage.show();

        optionScene.getStylesheets().add("MainWindow.css");

        Label label = new Label();
        label.setText("SELECT DIFFICULTY");
        vbox.getChildren().add(label);

        btnEasy.setText("EASY");
        btnEasy.setPrefWidth(200);
        btnEasy.setOnAction(e -> onEasyClicked(e));
        vbox.getChildren().add(btnEasy);

        btnMedium.setText("MEDIUM");
        btnMedium.setPrefWidth(200);
        btnMedium.setOnAction(e -> onMediumClicked(e));
        vbox.getChildren().add(btnMedium);

        btnHard.setText("HARD");
        btnHard.setPrefWidth(200);
        btnHard.setOnAction(e -> onHardClicked(e));
        vbox.getChildren().add(btnHard);

        btnCheat.setText("CHEAT");
        btnCheat.setPrefWidth(200);
        btnCheat.setOnAction(e -> onCheatClicked(e));
        vbox.getChildren().add(btnCheat);
    }

    void onEasyClicked(ActionEvent event) {

    }

    void onMediumClicked(ActionEvent event) {

    }

    void onHardClicked(ActionEvent event) {

    }

    void onCheatClicked(ActionEvent event) {

    }

    // Screen to display High Scores
    public void onScoresClicked() {
        VBox vbox = new VBox();
        vbox.setId("menu-background");
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        Scene scoreScene = new Scene(vbox, 800, 600);
        Stage scoreStage = new Stage();
        scoreStage.setScene(scoreScene);
        scoreStage.setTitle("High Scores");
        scoreStage.show();

        scoreScene.getStylesheets().add("MainWindow.css");

        Label label = new Label();
        label.setText("HIGH SCORES");
        vbox.getChildren().add(label);

        // temp manual placement of players
        highScoreList.getList().add(player1);
        highScoreList.getList().add(player2);
        highScoreList.getList().add(player3);
        highScoreList.sort();
        for (HighScore items : highScoreList.getList()) {

            HBox Scorehbox = new HBox();
            Scorehbox.setPadding(new Insets(10));
            Scorehbox.setSpacing(10);
            Scorehbox.setAlignment(Pos.CENTER);

            // Displaying the name and score properly in the High Score Screen
            Label names = new Label();
            names.setText(items.getName());
            Scorehbox.getChildren().add(names);
            Label dots = new Label();
            dots.setText(" ............ ");
            Scorehbox.getChildren().add(dots);
            Label scores = new Label();
            scores.setText(String.valueOf(items.getScore()));
            Scorehbox.getChildren().add(scores);

            vbox.getChildren().add(Scorehbox);
        }
    }
}
