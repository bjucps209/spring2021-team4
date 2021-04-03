package model;

import java.util.ArrayList;
import java.io.*;
import model.Enums.*;
import model.GameObjects.EnemyObject;
import model.GameObjects.GameObject;
import model.GameObjects.Obstacle;
import model.GameObjects.Player;

public class Wave {

    // Singleton variable
    private static Wave wave = null;

    // Constant variables
    private final int windowWidth = 0;
    private final int windowHeight = 0;

    // Data variables
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<HighScore> highScores = new ArrayList<HighScore>();
    private ArrayList<ShipSkins> skins = new ArrayList<ShipSkins>();

    // Menu variables
    private Menu mainMenu = Menu.getInstance();
    private Game game;

    // Shop variables
    private ShipSkins currentShip;

    // Singleton constructor
    private Wave() {
        mainMenu.initialize(); // construct main menu info
    }

    // Starts the game, does all calculations and initializes lists
    public void gameStart() {
        game = new Game(100, 100);
    }

    // Ends the game and clears variables
    public void endGame() {

    }

    // Loads user to instance variables
    public void loadUser() {

    }

    // Saves user
    public void saveUser() {

    }

    // Singleton get instance method
    public static Wave getInstance() {
        if (wave == null) {
            wave = new Wave();
        }

        return wave;
    }

    public Game getGame(){
        return this.game;
    }

    public int getWindowWidth() {
        return this.windowWidth;
    }

    public int getWindowHeight() {
        return this.windowHeight;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<HighScore> getHighScores() {
        return this.highScores;
    }

    public void setHighScores(ArrayList<HighScore> highScores) {
        this.highScores = highScores;
    }

    public ArrayList<ShipSkins> getSkins() {
        return this.skins;
    }

    public void setSkins(ArrayList<ShipSkins> skins) {
        this.skins = skins;
    }

    public ShipSkins getCurrentShip() {
        return this.currentShip;
    }

    public void setCurrentShip(ShipSkins currentShip) {
        this.currentShip = currentShip;
    }

    public void save(String userName){
        // Note: userName will be uses as the fileName follow by ".dat"
        try(DataInputStream rd = new DataInputStream(new FileInputStream(userName+".dat"))){
            // load data here
        }catch(IOException e){
            
        }
    }
    
    public void load(String userName){
        // Note: userName will be uses as the fileName follow by ".dat"
        try(DataInputStream rd = new DataInputStream(new FileInputStream(userName+".dat"))){

        }catch(IOException e){

        }
    }

    public Menu getMainMenu() {
        return mainMenu;
    }


}
