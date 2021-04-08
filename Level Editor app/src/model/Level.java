package model;

import java.util.ArrayList;

import model.GameObjects.EnemyObject;
import model.GameObjects.GameObject;
import model.GameObjects.Obstacle;
import model.GameObjects.Player;

public class Level {
    // Class that holds all info for 1 level

    // Object lists
    private ArrayList<GameObject> allObjects = new ArrayList<GameObject>();
    private ArrayList<EnemyObject> enemies = new ArrayList<EnemyObject>();
    private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

    // Data info
    private int score;

    // Gameplay variables
    private Player player;


    private int remainingTime;  // possible connect to TimeLine() with data binding technique

    public Level() {
        initialize();
    }

    // start all info necessary for level
    public void initialize() {
        spawnPlayer();
        spawnEnemies();
    }

    // creates obstacles
    public void createObstacles() {

    }

    // spawns player
    public void spawnPlayer() {
        player = new Player();
        allObjects.add(player);
    }

    // spawns enemies
    public void spawnEnemies() {
        
    }

    // starts enemy movements
    public void start() {

    }

    // stops enemies and removes them and obstacles
    public void stop() {

    }

    // opens wall at the end for player to progress to the next level through
    public void openWall() {

    }

    public ArrayList<GameObject> getAllObjects() {
        return allObjects;
    }

    public void setAllObjects(ArrayList<GameObject> allObjects) {
        this.allObjects = allObjects;
    }

    public ArrayList<EnemyObject> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<EnemyObject> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }
}
