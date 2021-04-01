package model;

import java.util.ArrayList;

import model.GameObjects.EnemyObject;
import model.GameObjects.GameObject;
import model.GameObjects.Obstacle;

public class Level {
    // Class that holds all info for 1 level

    // Object lists
    private ArrayList<GameObject> allObjects = new ArrayList<GameObject>();
    private ArrayList<EnemyObject> enemies = new ArrayList<EnemyObject>();
    private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

    // Data info
    private Timer timer;
    private int score;
}
