//-----------------------------------------------------------
//File:   GameWindow.java
//Desc:   this file represents the window in which the game 
//        will be played
//-----------------------------------------------------------

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.GameObjects.GameObject;
import model.GameObjects.Player;
import model.GameObjects.Enemies.EnemyObject;
import model.GameObjects.Obstacles.Obstacle;
import model.GameObjects.Powerups.PowerUp;
import model.Game;
import model.HighScore;
import model.HighScoreList;
import model.Level;
import model.Wave;
import model.Enums.ShipSkins;

// the controller for the fxml file on which the game is played out
public class GameWindow {

    static Wave w;  // Singleton variable for Wave
    static Player p; // player variable
    static Game g;   // the current game variable

    static HighScoreList highScoreList = new HighScoreList(); // the List for high score screen
    static boolean pauseState = false;                      // boolean indicator for stop/resume of game

    public boolean levelStopped = false;                    // boolean indicator for end game
    public boolean levelIsNext = false;                     // boolean indicator for progressing to next level

    @FXML

    Pane pane;

    static Timeline timer;
    static Timeline countDown;
    static VBox vboxName;
    static Scene nameScene;
    static Button btnResume;
    static Button btnEnd;

    // GUI needed for every level
    Label lblTime = new Label("TIME REMAINING");
    Label lblTimer = new Label();

    // this is the only pair that wont be reset every level
    Label lblHealth = new Label("HEALTH");
    ProgressBar healthBar = new ProgressBar();

    Label lblSCORE = new Label("SCORE");
    Label lblScore = new Label();

    ImageView rightArrowImageView;
    ImageView playerImageView;

    /**
     * initialize method to load the current level of the instance of game
     * 
     * @param none
     * @return none
     */
    @FXML
    public void initialize() {

        w = Wave.getInstance();
        Boolean loadSuccess = false; // to see if the file load successfully
        if (Wave.getInstance().isResumeGame()) {
            Wave.getInstance().setResumeGame(false);
            ArrayList<Level> s = new ArrayList<>();
            s.add(new Level());

            w.setGame(new Game(1000, 800, s));
            g = w.getGame();
            loadSuccess = g.load(Wave.getInstance().getCurrentUser().getName());
            if (loadSuccess) {
            
                g.startHitDetection();

            } else {
                var alert = new Alert(AlertType.ERROR,
                        "Error occurs while loading the file, start a new game instead!");

                alert.show();

                // start new game
                w.setCoins(0);
                ArrayList<Level> levels = w.getDefaultLevels();
                w.gameStart(levels);

            }

        } else if (MainWindow.customGameLevels.size() != 0) {
            w.setCoins(0);
            w.gameStart(MainWindow.customGameLevels);

        } else {
            w.setCoins(0);
            ArrayList<Level> levels = w.getDefaultLevels();
            w.gameStart(levels);
        }

        g = w.getGame();
        p = g.getCurrentLevel().getPlayer();
        spawnEntities();

        // pane dimensions
        pane.setPrefSize(1000, 800);

        // Label to represent the timer
        g.getCurrentLevel().setRemainingTime(g.getCurrentLevel().getRemainingTime());
        lblTimer.textProperty().bind(g.getCurrentLevel().remainingTimeProperty().asString());
        pane.getChildren().add(lblTime);
        pane.getChildren().add(lblTimer);
        lblTime.relocate(0, 60);
        lblTimer.relocate(0, 80);
        // timer to connect to the countdown in game.
        countDown = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            g.getCurrentLevel().setRemainingTime(g.getCurrentLevel().getRemainingTime() - 1);
            w.setCoins(w.getCoins() + 10);
        }));
        countDown.setCycleCount(g.getCurrentLevel().getRemainingTime());
        countDown.play();
        healthBar.progressProperty()
                .bind(Bindings.createDoubleBinding(() -> (double) p.getHealth() / 100, p.healthProperty()));
        pane.getChildren().add(lblHealth);
        pane.getChildren().add(healthBar);
        healthBar.relocate(0, 30);
        lblScore.textProperty().bind(w.coinsProperty().asString());
        pane.getChildren().add(lblSCORE);
        pane.getChildren().add(lblScore);
        lblSCORE.relocate(900, 0);
        lblScore.relocate(900, 20);

        // Getting the game to update at 16.7ms or ~60fps
        timer = new Timeline(new KeyFrame(Duration.millis(16.7), e -> {
            if (g.isWon()) {
                timer.stop();
                countDown.stop();
                for (EnemyObject item : g.getCurrentLevel().getEnemies()) {
                    item.pause();
                }
                highScoreList.getList().add(new HighScore(w.getCurrentUser().getName(), w.getCoins()));
                highScoreList.save();
                Wave.getInstance().saveAllUsers(); // save user
                Wave.getInstance().setCoins(0); // the running score
                var vboxEnd = new VBox();
                vboxEnd.setPadding(new Insets(10));
                vboxEnd.setSpacing(10);
                vboxEnd.setAlignment(Pos.CENTER);

                var endScene = new Scene(vboxEnd, 800, 600);
                Stage endStage = new Stage();
                endStage.setScene(endScene); // set the scene
                endStage.setTitle("End Screen");
                endStage.setAlwaysOnTop(true);
                endStage.show();

                endScene.getStylesheets().add("GameWindow.css");

                Label label = new Label("YOU WIN!");
                Label label2 = new Label("Please return to the title screen");
                vboxEnd.getChildren().add(label);
                vboxEnd.getChildren().add(label2);
            } else {
                g.update();
                Node nodeToRemove = null;
                for (Node n : pane.getChildren()) {
                    if (n.getUserData() instanceof EnemyObject
                            && !g.getCurrentLevel().getEnemies().contains(n.getUserData())) {
                        nodeToRemove = n;
                    }
                }
                if (nodeToRemove != null) {
                    pane.getChildren().remove(nodeToRemove);
                }
                for (Node n : pane.getChildren()) {
                    if (n.getUserData() instanceof PowerUp
                            && !g.getCurrentLevel().getPowerUps().contains(n.getUserData())) {
                        nodeToRemove = n;
                    }
                }
                if (nodeToRemove != null) {
                    pane.getChildren().remove(nodeToRemove);
                }
                if (g.getCurrentLevel().getRemainingTime() <= 0) {
                    countDown.stop();
                    g.getCurrentLevel().setFinished(true);
                }
                if (g.getCurrentLevel().isFinished()) {
                    stopLevel();
                }
                if (g.getCurrentLevel().getPlayer().moveOn) {
                    startLevel();
                }

                // Checks to see if player died
                if (healthBar.getProgress() == 0) {
                    endGameOnHealth();
                }
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    /**
     * method called to showt stop the level. it also shows how to proceed to the
     * next level
     * 
     * @param none
     * @return none
     */
    public void stopLevel() {

        if (!levelStopped) {
            // Code for stopping level and preparing for move on
            g.stopHitDetection();
            p.setTemporaryInvincible(true);
            for (EnemyObject item : g.getCurrentLevel().getEnemies()) {
                item.pause();
            }
            p = g.getCurrentLevel().getPlayer();
            p.setWinState(true);

            Image rightArrowImage = new Image("./Images/arrow.png");
            rightArrowImageView = new ImageView(rightArrowImage);
            rightArrowImageView.setFitWidth(50);
            rightArrowImageView.setFitHeight(50);
            pane.getChildren().add(rightArrowImageView);
            rightArrowImageView.relocate(900, 300);

            // lets next level be stopped
            levelStopped = true;
        }
    }

    /**
     * used in GameWindow's initialize to start the game
     * 
     * @param none
     * @return none
     */
    public void startLevel() {
        ArrayList<Node> toRemove = new ArrayList<Node>();
        // Code to start a level
        if (!levelIsNext) {
            p.setTemporaryInvincible(false);
            g.stopHitDetection();
            g.stopPlayerHitDetection();
            int health = p.getHealth();
            g.nextLevel();
            p = g.getCurrentLevel().getPlayer();
            p.setHealth(health);
            healthBar.progressProperty()
                    .bind(Bindings.createDoubleBinding(() -> (double) p.getHealth() / 100, p.healthProperty()));
            try {
                g.startHitDetection();
            } catch (Exception e) {
                
            }
            
            lblTimer.textProperty().bind(g.getCurrentLevel().remainingTimeProperty().asString());

            pane.getChildren().remove(rightArrowImageView);
            for (var item : pane.getChildren()) {
                if (item.getUserData() instanceof GameObject) {
                    toRemove.add(item);
                }
            }

            for (Node n : toRemove) {
                pane.getChildren().remove(n);
            }

            toRemove = new ArrayList<Node>();

            // start the timer
            countDown.play();

            pane.getChildren().remove(playerImageView);
            spawnEntities();

            levelStopped = false;
            levelIsNext = false;
        }

    }

    /**
     * calls the methods to spawn all types of entities in the pane
     * 
     * @param none
     * @return none
     */
    public void spawnEntities() {
        // code to combine all spawn function below
        spawnObstacles();
        spawnPowerups();
        spawnPlayer();
        spawnEnemies();
    }

    /**
     * spawns the player and binds a corresponding imageView to it
     * 
     * @param none
     * @return none
     */
    public void spawnPlayer() {
        p = g.getCurrentLevel().getPlayer();
        Image playerImage;
        ShipSkins currentSkin = w.getCurrentUser().getShip();
        switch (currentSkin) {
        case SHIP2:
            playerImage = new Image("./Images/playerShip1_green.png");
            break;
        case SHIP3:
            playerImage = new Image("./Images/playerShip1_orange.png");
            break;
        case SHIP4:
            playerImage = new Image("./Images/playerShip1_red.png");
            break;
        case SHIP5:
            playerImage = new Image("./Images/playerShip2_blue.png");
            break;
        case SHIP6:
            playerImage = new Image("./Images/playerShip2_green.png");
            break;
        case SHIP7:
            playerImage = new Image("./Images/playerShip2_orange.png");
            break;
        case SHIP8:
            playerImage = new Image("./Images/playerShip2_red.png");
            break;
        case SHIP9:
            playerImage = new Image("./Images/playerShip3_blue.png");
            break;
        case SHIP10:
            playerImage = new Image("./Images/playerShip3_green.png");
            break;
        case SHIP11:
            playerImage = new Image("./Images/playerShip3_orange.png");
            break;
        case SHIP12:
            playerImage = new Image("./Images/playerShip3_red.png");
            break;
        case SHIP13:
            playerImage = new Image("./Images/ufoBlue.png");
            break;
        case SHIP14:
            playerImage = new Image("./Images/ufoGreen.png");
            break;
        case SHIP15:
            playerImage = new Image("./Images/ufoYellow.png");
            break;
        case SHIP16:
            playerImage = new Image("./Images/ufoRed.png");
            break;
        default:
            playerImage = new Image("./Images/playerShip1_blue.png");
            break;
        }

        // playerImage = new Image("./Images/playerShip1_blue.png");
        playerImageView = new ImageView(playerImage);
        playerImageView.setFitWidth(p.getWidth());
        playerImageView.setFitHeight(p.getHeight());
        playerImageView.layoutXProperty().bind(p.xProperty());
        playerImageView.layoutYProperty().bind(p.yProperty());
        playerImageView.setUserData(p);
        pane.getChildren().add(playerImageView);
    }

    /**
     * iterates through the list of enemy entities in the current level and 'spawns'
     * them in the pane
     * 
     * @param none
     * @return none
     */
    public void spawnEnemies() {
        for (EnemyObject o : g.getLevels().get(g.getLevelNum()).getEnemies()) {
            switch (o.getType()) {
            case BOUNCER:
                Image bouncerImage = new Image("./Images/enemyBlack4.png");
                ImageView bouncerImageView = new ImageView(bouncerImage);
                bouncerImageView.setFitWidth(o.getWidth());
                bouncerImageView.setFitHeight(o.getHeight());
                pane.getChildren().add(bouncerImageView);
                bouncerImageView.layoutXProperty().bind(o.xProperty());
                bouncerImageView.layoutYProperty().bind(o.yProperty());
                bouncerImageView.setUserData(o);
                break;
            case GHOST:
                Image ghostImage = new Image("./Images/enemyBlack2.png");
                ImageView ghostImageView = new ImageView(ghostImage);
                ghostImageView.setFitWidth(o.getWidth());
                ghostImageView.setFitHeight(o.getHeight());
                pane.getChildren().add(ghostImageView);
                ghostImageView.layoutXProperty().bind(o.xProperty());
                ghostImageView.layoutYProperty().bind(o.yProperty());
                ghostImageView.setUserData(o);
                ghostImageView.setOpacity(0.25);
                break;
            case TRACKER:
                Image trackerImage = new Image("./Images/enemyBlack1.png");
                ImageView trackerImageView = new ImageView(trackerImage);
                trackerImageView.setFitWidth(o.getWidth());
                trackerImageView.setFitHeight(o.getHeight());
                pane.getChildren().add(trackerImageView);
                trackerImageView.layoutXProperty().bind(o.xProperty());
                trackerImageView.layoutYProperty().bind(o.yProperty());
                trackerImageView.setUserData(o);
                break;
            default:
                break;
            }
        }
    }

    /**
     * iterates through the list of obstacles in the current level and 'spawns' them
     * in the pane
     * 
     * @param none
     * @return none
     */
    public void spawnObstacles() {
        System.out.println(System.getProperty("user.dir"));
        for (Obstacle o : g.getLevels().get(g.getLevelNum()).getObstacles()) {
            switch (o.getType()) {
            case SQUARE:
                Image squareImage = new Image("./Images/block_square.png");
                ImageView squareImageView = new ImageView(squareImage);
                squareImageView.setFitWidth(o.getWidth());
                squareImageView.setFitHeight(o.getHeight());
                pane.getChildren().add(squareImageView);
                squareImageView.layoutXProperty().bind(o.xProperty());
                squareImageView.layoutYProperty().bind(o.yProperty());
                squareImageView.setUserData(o);
                break;
            case LARGE:
                Image ghostImage = new Image("./Images/block_large.png");
                ImageView ghostImageView = new ImageView(ghostImage);
                ghostImageView.setFitWidth(o.getWidth());
                ghostImageView.setFitHeight(o.getHeight());
                pane.getChildren().add(ghostImageView);
                ghostImageView.layoutXProperty().bind(o.xProperty());
                ghostImageView.layoutYProperty().bind(o.yProperty());
                ghostImageView.setUserData(o);
                break;
            case NARROW:
                Image laserImage = new Image("./Images/block_narrow.png");
                ImageView laserImageView = new ImageView(laserImage);
                laserImageView.setFitWidth(o.getWidth());
                laserImageView.setFitHeight(o.getHeight());
                pane.getChildren().add(laserImageView);
                laserImageView.layoutXProperty().bind(o.xProperty());
                laserImageView.layoutYProperty().bind(o.yProperty());
                laserImageView.setUserData(o);
                break;
            default:
                break;
            }
        }
    }

    /**
     * iterates through the list of powerups in the current level and 'spawns' them
     * in the pane
     * 
     * @param none
     * @return none
     */
    public void spawnPowerups() {
        for (PowerUp power : g.getLevels().get(g.getLevelNum()).getPowerUps()) {
            switch (power.getType()) {
            case HealthGainBig:
                Image healthBig = new Image("./Images/pill_yellow.png");
                ImageView healthBigImageView = new ImageView(healthBig);
                healthBigImageView.setFitWidth(power.getWidth());
                healthBigImageView.setFitHeight(power.getHeight());
                pane.getChildren().add(healthBigImageView);
                healthBigImageView.layoutXProperty().bind(power.xProperty());
                healthBigImageView.layoutYProperty().bind(power.yProperty());
                healthBigImageView.setUserData(power);
                break;
            case HealthGainSmall:
                Image healthSmall = new Image("./Images/pill_blue.png");
                ImageView healthImageView = new ImageView(healthSmall);
                healthImageView.setFitWidth(power.getWidth());
                healthImageView.setFitHeight(power.getHeight());
                pane.getChildren().add(healthImageView);
                healthImageView.layoutXProperty().bind(power.xProperty());
                healthImageView.layoutYProperty().bind(power.yProperty());
                healthImageView.setUserData(power);
                break;
            case DestroyShip:
                Image destroyShip = new Image("./Images/bolt_gold.png");
                ImageView destroyShipImageView = new ImageView(destroyShip);
                destroyShipImageView.setFitWidth(power.getWidth());
                destroyShipImageView.setFitHeight(power.getHeight());
                pane.getChildren().add(destroyShipImageView);
                destroyShipImageView.layoutXProperty().bind(power.xProperty());
                destroyShipImageView.layoutYProperty().bind(power.yProperty());
                destroyShipImageView.setUserData(power);
                break;
            case Freeze:
                Image freeze = new Image("./Images/powerupBlue_bolt.png");
                ImageView freezeImageView = new ImageView(freeze);
                freezeImageView.setFitWidth(power.getWidth());
                freezeImageView.setFitHeight(power.getHeight());
                pane.getChildren().add(freezeImageView);
                freezeImageView.layoutXProperty().bind(power.xProperty());
                freezeImageView.layoutYProperty().bind(power.yProperty());
                freezeImageView.setUserData(power);
                break;
            case TemporaryInvincible:
                Image tempInvincible = new Image("./Images/shield_gold.png");
                ImageView invincibleImageView = new ImageView(tempInvincible);
                invincibleImageView.setFitWidth(power.getWidth());
                invincibleImageView.setFitHeight(power.getHeight());
                pane.getChildren().add(invincibleImageView);
                invincibleImageView.layoutXProperty().bind(power.xProperty());
                invincibleImageView.layoutYProperty().bind(power.yProperty());
                invincibleImageView.setUserData(power);
                break;
            default:
                break;
            }
        }
    }

    /**
     * method that detects if the player is out of health. if so, ends the game and
     * notifies the user
     * 
     * @param none
     * @return none
     */
    void endGameOnHealth() {
        timer.stop();
        countDown.stop();
        for (EnemyObject item : g.getCurrentLevel().getEnemies()) {
            item.pause();
        }
        Wave.getInstance().saveAllUsers(); // save user
        Wave.getInstance().setCoins(0); // the running score
        var vboxEnd = new VBox();
        vboxEnd.setPadding(new Insets(10));
        vboxEnd.setSpacing(10);
        vboxEnd.setAlignment(Pos.CENTER);

        var endScene = new Scene(vboxEnd, 800, 600);
        Stage endStage = new Stage();
        endStage.setScene(endScene); // set the scene
        endStage.setTitle("End Screen");
        endStage.setAlwaysOnTop(true);
        endStage.show();

        endScene.getStylesheets().add("GameWindow.css");

        Label label = new Label("GAME OVER!");
        Label label2 = new Label("Please return to the title screen");
        vboxEnd.getChildren().add(label);
        vboxEnd.getChildren().add(label2);
    }

    /**
     * method for pausing the game and ending the game
     * 
     * @param none
     * @return none
     */
    public static void pause() {
        if (pauseState == false) {
            pauseState = true;
            for (EnemyObject item : g.getCurrentLevel().getEnemies()) {
                item.pause();
            }
            timer.pause();
            countDown.pause();

            // Opens window to allow player to enter their name
            vboxName = new VBox();
            vboxName.setPadding(new Insets(10));
            vboxName.setSpacing(10);
            vboxName.setAlignment(Pos.CENTER);

            nameScene = new Scene(vboxName, 800, 600);
            Stage nameStage = new Stage();
            nameStage.setScene(nameScene); // set the scene
            nameStage.setTitle("Name Menu");
            nameStage.setAlwaysOnTop(true);
            nameStage.show();

            nameScene.getStylesheets().add("GameWindow.css");

            btnResume = new Button("RESUME");
            btnResume.setOnAction(e -> onResumeClicked(e));
            vboxName.getChildren().add(btnResume);

            btnEnd = new Button("END GAME");
            btnEnd.setOnAction(e -> onEndClicked(e));
            vboxName.getChildren().add(btnEnd);

        }
    }

    /**
     * close the window and start all necessary game timers
     * 
     * @param e is used to allow for btn.setOnAction(e -> onResumeClicked(e)) to
     *          compile
     * @return none
     */
    static void onResumeClicked(ActionEvent event) {

        for (EnemyObject item : g.getCurrentLevel().getEnemies()) {
            item.start();
        }
        timer.play();
        countDown.play();
        Stage stage = (Stage) btnResume.getScene().getWindow();
        stage.close();
        pauseState = false;

    }

    /**
     * close the window and end the game, saving everything
     * 
     * @param e is used to allow for btn.setOnAction( e -> onEndClicked(e)) to
     *          compile
     * @return none
     */
    static void onEndClicked(ActionEvent event) {
        Wave.getInstance().getGame().save(Wave.getInstance().getCurrentUser().getName());
        Wave.getInstance().saveAllUsers();
        highScoreList.getList().add(new HighScore(w.getCurrentUser().getName(), w.getCoins()));
        pauseState = false;
        highScoreList.save();
        // close the current window and game window
        Stage stage = (Stage) btnEnd.getScene().getWindow();
        stage.close();
    }

    /**
     * close the timer
     * 
     * @param none
     * @return none
     */
    public static void onClosed() {
        timer.stop();
        countDown.stop();

    }

    /**
     * move methods of GameWindow that correspond with moving the player
     * 
     * @param none
     * @return none
     */
    public static void moveUp() {
        if (p.getDy() == 0) {
            p.moveUp();
            g.update();
        }
    }

    public static void moveDown() {
        if (p.getDy() == 0) {
            p.moveDown();
            g.update();
        }
    }

    public static void moveRight() {
        if (p.getDx() == 0) {
            p.moveRight();
            g.update();
        }
    }

    public static void moveLeft() {
        if (p.getDx() == 0) {
            p.moveLeft();
            g.update();
        }
    }

    public static void moveNeutral() {
        p.moveNeutral();
        g.update();
    }
    // ----
}