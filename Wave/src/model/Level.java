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

    public Level() {

    }

    // creates obstacles
    public void createObstacles() {

    }

    // spawns player
    public void spawnPlayer() {

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
}
