package model;

import java.util.ArrayList;

import model.Enums.EnemyTypes;
import model.GameObjects.EnemyObject;
import model.GameObjects.Player;

public class Game {
    // Class that handles all game parts after game has started

    // Visual variables
    private int gameWidth;
    private int gameHeight;

    // Data variables
    private ArrayList<Level> levels = new ArrayList<Level>();

    // Gameplay variables
    private Player player;
    private Level currentLevel;
    private int levelNum = 0;

    // Constructor
    public Game(int width, int height) {
        gameWidth = width;
        gameHeight = height;
        currentLevel = levels.get(levelNum);
        levelNum = 0;
        initializeLevel(currentLevel);
    }

    // initializes game with current level
    public void initializeLevel(Level l) {

    }

    // creates all hard-coded levels and stores in arraylist
    public void createLevels() {

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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
