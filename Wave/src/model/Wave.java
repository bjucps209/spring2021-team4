package model;

import java.util.ArrayList;

import model.Enums.*;
import model.GameObjects.GameObject;
import model.GameObjects.Player;

public class Wave {

    // Singleton variable
    private static Wave wave = null;

    // Constant variables
    private final int windowWidth = 0;
    private final int windowHeight = 0;
    private final int gameWidth = 0;
    private final int gameHeight = 0;

    // Data variables
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<HighScore> highScores = new ArrayList<HighScore>();
    private ArrayList<ShipSkins> skins = new ArrayList<ShipSkins>();

    // Menu variables
    private User currentUser;
    private int coins;

    // Shop variables
    private ShipSkins currentShip;

    // Gameplay variables
    private ArrayList<GameObject> allObjects = new ArrayList<GameObject>();
    private Player player;

    // Singleton constructor
    private Wave() {

    }

    // Singleton get instance method
    public static Wave getInstance() {
        if (wave == null) {
            wave = new Wave();
        }

        return wave;
    }
}
