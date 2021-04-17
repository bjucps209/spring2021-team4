package model;

import java.util.ArrayList;
import java.io.*;
import model.GameObjects.GameObject;
import model.GameObjects.Obstacles.*;
import model.GameObjects.*;
import model.GameObjects.Enemies.EnemyObject;

public class Game {
    // Class that handles all game parts after game has started
    Wave w;

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
        w = Wave.getInstance();
        gameWidth = width;
        gameHeight = height;
        try {
            createLevels();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        currentLevel = levels.get(levelNum);
    }

    // Main game update function for updating each object
    public void update() {
        for (GameObject g : levels.get(levelNum).getAllObjects()) {
            g.update();
        }
    }

    public void startHitDetection() {
        for (GameObject g : levels.get(levelNum).getAllObjects()) {
            g.startHitDetection();
        }
    }

    public void stopHitDetection() {
        for (GameObject g : levels.get(levelNum).getAllObjects()) {
            try {
                g.stopHitDetection();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // creates all hard-coded levels and stores in arraylist
    public void createLevels() throws IOException {
        Level l = w.loadCustomLevel("level1");
        levels.add(l);
    }

    // progresses to next level information
    public void nextLevel() {
        levelNum++;
        currentLevel = levels.get(levelNum);
    }

    // --- setters ---

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public void setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
    }

    public void setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }

    // --- getters ---
    public Level getCurrentLevel() {
        return currentLevel;
    }

    public int getLevelNum() {
        return levelNum;
    }

    public int getGameWidth() {
        return this.gameWidth;
    }

    public int getGameHeight() {
        return this.gameHeight;
    }

    public ArrayList<Level> getLevels() {
        return this.levels;
    }

    // Methods for serialization
    /**
     * The methods will load the file give by @param userName in txt form into
     * program
     * 
     * @param userName - the username or name of file been saved
     * @return true if successfully loads data into the program, false otherwise
     */
    public boolean load(String userName) {
        // Warning, loading user has not been implement yet

        try (BufferedReader rd = new BufferedReader(new FileReader(userName + ".txt"))) {
            // load data here

            // TODO potential cause problem here if all level is been loaded
            // TODO: need to call and load all level from level editor first
            this.levels = new ArrayList<Level>();
            for (int i = 0; i < 10; i++) {
                levels.add(new Level());
            }

            String firstLine = rd.readLine();
            if (firstLine.equals("###END###")) {
                // means already end of game
                this.levelNum = this.levels.size() - 1;
                this.currentLevel = this.levels.get(levelNum);
                this.currentLevel.setRemainingTime(0);

                // Empyt all enemy and obstacle and powerups
                this.currentLevel.setAllObjects(new ArrayList<GameObject>());
                this.currentLevel.setEnemies(new ArrayList<EnemyObject>());
                this.currentLevel.setObstacles(new ArrayList<Obstacle>());
                // TODO: power up

                // set to last level in game, but yet already at the end of that level
                // TODO: still need further discussion on how to handle it later

                return true;
            }

            // when it is not end of game
            int remainingTime = Integer.parseInt(firstLine);
            levelNum = Integer.parseInt(rd.readLine());
            this.currentLevel = this.levels.get(levelNum);
            currentLevel.setRemainingTime(remainingTime);

            // reset everything
            this.currentLevel.setAllObjects(new ArrayList<GameObject>());
            this.currentLevel.setEnemies(new ArrayList<EnemyObject>());
            this.currentLevel.setObstacles(new ArrayList<Obstacle>());

            boolean result = currentLevel.deserialization(rd);

            String nextLine = rd.readLine();
            if (nextLine.equals("ENDL#") == false) {
                // should already be end of file, yet still consist of data
                throw new IOException("File format error");
            }

            return true;
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * The program will save the current game status into a txt file name
     * after @param userName
     * 
     * @param userName - the name of the file that is going to be save
     * @return - true if successfully save the program into the file, false
     *         otherwise
     */
    public boolean save(String userName) {
        // Note: userName will be uses as the fileName follow by ".dat"
        try (PrintWriter wd = new PrintWriter(new FileWriter(userName + ".txt"))) {

            int remainingTime = this.currentLevel.getRemainingTime();
            if (remainingTime <= 0 && this.levelNum + 1 < this.levels.size()) {
                // means end of game
                // automatically move to next leve
                wd.println("60"); // write remaining time for each level to be 60
                wd.println(this.levelNum + 1); // move to next level
            } else if (remainingTime <= 0 && this.levelNum + 1 == this.levels.size()) {
                // Means the player save at the end of last level of the game
                wd.print("###END###");
                return true;
            } else {
                // means save during the game
                wd.println(remainingTime);
                wd.println(this.levelNum);
            }

            // Hard coding here, since only have one player mode
            // need to change in beta version, when there is a difficulty level
            // right not assume is in easy mode
            // TODO: difficutly level
            wd.println("easy");

            // need to change when two player mode add
            /*
             * wd.println("1"); // indicate only one player mode int totalUser = 1;
             * 
             * 
             * // determine number of player mode if(totalUser == 1){ // single player mode
             * // also need user info //
             * username;totalCoins;score;shipskin;x;y;dx;dy;sepcial effects
             * wd.println("###user"); User currentUser =
             * Wave.getInstance().getCurrentUser(); String info = currentUser.getName() +
             * ";" + currentUser.getCoins() + ";" + currentLevel.getScore() + ";" +
             * currentLevel.getPlayer().serialize(); wd.println(info); }else{ //
             * score;shipskin;x;y;dx;dy;special effects for(int i = 0; i < totalUser; i++){
             * wd.println("###user"); // // TODO how to record each player info!!! } }
             */

            for (String s : currentLevel.serialization().split("/n")) {
                wd.println(s);
            }
            // wd.print(currentLevel.serialization());
            wd.println("ENDL#");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
