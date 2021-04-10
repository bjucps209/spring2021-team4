import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.HighScore;
import model.HighScoreList;
import model.Wave;
import model.GameObjects.Player;


public class MainWindow {
    Wave w;

    HighScore player1 = new HighScore("Bob", 100);
    HighScore player2 = new HighScore("Amanda", 300);
    HighScore player3 = new HighScore("Tim", 200);
    HighScoreList highScoreList = new HighScoreList(new ArrayList<HighScore>());

    // Difficulty Buttons
    Button btnEasy = new Button();
    Button btnMedium = new Button();
    Button btnHard = new Button();
    Button btnCheat = new Button();

    @FXML
    public void initialize() {
        w = Wave.getInstance();
        w.gameStart();
    }

    @FXML
    public void onNewGameClicked() throws IOException {
        // opens up new window which is GameWindow
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        Scene scene = new Scene(loader.load());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                if (k.getEventType() == KeyEvent.KEY_PRESSED) {
                    switch (k.getCode()) {
                        case UP:
                            g.moveUp();
                        case DOWN:
                            g.moveDown();
                        case LEFT:
                            g.moveLeft();
                        case RIGHT:
                            g.moveRight();
                    }
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                g.moveNeutral();
            }
        });
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Wave");
        stage.show();
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

        String INFO = "You can begin the game by clicking on NEW GAME.\n" +
                      "\n" +
                      "When playing the game keep this in mind:\n" +
                      "- You will lose health if you are hit by an enemy.\n" +
                      "- You can run into powerups that will give you an advantage.\n";

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
        lblPlayer.setText("Move Left and Right: left arrow / right arrow\n" +
                          "Move up and down: up arrow / down arrow");

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

    @FXML
    public void onDisplayInfo() {
        Player p = w.getGame().getCurrentLevel().getPlayer();
        p.moveDown();
        p.moveLeft();
        w.getGame().update();
        System.out.println(p.toString());
    }
}
