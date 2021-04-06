package model;

import java.util.ArrayList;
import java.io.*;
import model.Enums.EnemyTypes;
import model.GameObjects.EnemyObject;
import model.GameObjects.GameObject;
import model.GameObjects.Player;

public class Game {
    // Class that handles all game parts after game has started

    // Visual variables
    private int gameWidth;
    private int gameHeight;

    // Data variables
    private ArrayList<Level> levels = new ArrayList<Level>();

    // Gameplay variables
    private Level currentLevel;
    private int levelNum = 0;

    // Constructor
    public Game(int width, int height) {
        gameWidth = width;
        gameHeight = height;
        createLevels();
        currentLevel = levels.get(levelNum);
        initializeLevel(currentLevel);
    }


    // Main game update function for updating each object
    public void update() {
        for (GameObject g : levels.get(levelNum).getAllObjects()) {
            g.update();
        }
    }

    // initializes game with current level
    public void initializeLevel(Level l) {

    }

    // creates all hard-coded levels and stores in arraylist
    public void createLevels() {
        Level l = new Level();
        levels.add(l);
    }

    // progresses to next level information
    public void nextLevel() {
        levelNum++;
        currentLevel = levels.get(levelNum);
        initializeLevel(currentLevel);
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public int getGameWidth() {
        return this.gameWidth;
    }

    public void setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
    }

    public int getGameHeight() {
        return this.gameHeight;
    }

    public void setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
    }

    public ArrayList<Level> getLevels() {
        return this.levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }


    // Methods for serialization
    public boolean save(String userName){
        // Note: userName will be uses as the fileName follow by ".dat"
        try(DataInputStream rd = new DataInputStream(new FileInputStream(userName+".dat"))){
            // load data here
            return true;
        }catch(IOException e){
            return false;
        }
    }
    
    public boolean load(String userName){
        // Note: userName will be uses as the fileName follow by ".dat"
        try(DataInputStream rd = new DataInputStream(new FileInputStream(userName+".dat"))){
            return true;
        }catch(IOException e){
            return false;
        }
    }

}
