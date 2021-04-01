package model;

import java.util.ArrayList;

import model.GameObjects.Player;

public class Game {
    // Class that handles all game parts after game has started

    // Singleton
    private static Game game = null;

    // Visual variables
    private int gameWidth;
    private int gameHeight;

    // Data variables
    private ArrayList<Level> levels = new ArrayList<Level>();

    // Gameplay variables
    private Player player;
    private Level currentLevel;

    private Game(int width, int height) {
        gameWidth = width;
        gameHeight = height;
    }

    // Return instance for normal use
    public static Game getInstance() {
        if (game == null) {
            throw new IllegalArgumentException("Please provide width and height parameters when using this method for the first time.");
        }
        return game;
    }

    // Returns first time user of Game
    public static Game getInstance(int width, int height) {
        if (game == null) {
            game = new Game(width, height);
            return game;
        } else {
            throw new IllegalArgumentException("Please do not use parameters after Game.getInstance() has already been used.");
        } 
    }
}
