package model;

import java.util.ArrayList;
import java.io.*;
import model.Enums.EnemyTypes;
import model.Enums.ShipSkins;
import model.GameObjects.GameObject;
import model.GameObjects.Obstacle;
import model.GameObjects.Player;
import model.GameObjects.Enemies.EnemyObject;

import model.GameObjects.*;
import model.GameObjects.Enemies.*;
import model.GameObjects.Powerups.*;

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

    public void startHitDetection() {
        for (GameObject g : levels.get(levelNum).getAllObjects()) {
            g.startHitDetection();
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
     * The methods will load the file give by @param userName in txt form into program
     * @param userName - the username or name of file been saved
     * @return true if successfully loads data into the program, false otherwise
     */
    public boolean load(String userName) {

        try (BufferedReader rd = new BufferedReader(new FileReader(userName + ".txt"))) {
            // load data here

            //TODO potential cause problem here if all level is been loaded
            this.levels = new ArrayList<Level>();
            for(int i = 0; i < 10; i++){
                levels.add(new Level());
            }

            String firstLine = rd.readLine();
            if(firstLine.equals("###END###")){
                // means already end of game
                this.levelNum = this.levels.size()-1;
                this.currentLevel = this.levels.get(levelNum);
                this.currentLevel.setRemainingTime(0);

                // Empyt all enemy and obstacle and powerups
                this.currentLevel.setAllObjects( new ArrayList<GameObject>());
                this.currentLevel.setEnemies(new ArrayList<EnemyObject>());
                this.currentLevel.setObstacles(new ArrayList<Obstacle>());
                //TODO: power up

                // set to last level in game, but yet already at the end of that level
                //TODO: still need further discussion on how to handle it later

       

                return true;
            }

            // when it is not end of game
            int remainingTime = Integer.parseInt(firstLine);
            levelNum = Integer.parseInt(rd.readLine());
            this.currentLevel = this.levels.get(levelNum);
            currentLevel.setRemainingTime(remainingTime);

            // reset everything
            this.currentLevel.setAllObjects( new ArrayList<GameObject>());
            this.currentLevel.setEnemies(new ArrayList<EnemyObject>());
            this.currentLevel.setObstacles(new ArrayList<Obstacle>());

            boolean result = currentLevel.deserialization(rd);

            
            String nextLine = rd.readLine();
            if(  nextLine.equals("ENDL#")== false){
                // should already be end of file, yet still consist of data
                throw new IOException ("File format error");
            }
            
            return true;
        } catch (IOException e) {
            return false;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * The program will save the current game status into a txt file name after @param userName
     * @param userName - the name of the file that is going to be save
     * @return - true if successfully save the program into the file, false otherwise
     */
    public boolean save(String userName) {
        // Note: userName will be uses as the fileName follow by ".dat"
        try (PrintWriter wd = new PrintWriter(new FileWriter(userName + ".txt"))) {
            
            int remainingTime = this.currentLevel.getRemainingTime();
            if(remainingTime <= 0 && this.levelNum+1 < this.levels.size()){
                // means end of game
                // automatically move to next leve
                wd.println("60"); // write remaining time for each level to be 60
                wd.println(this.levelNum+1); // move to next level
            }else if(remainingTime <= 0 && this.levelNum +1 == this.levels.size()){
                // Means the player save at the end of last level of the game
                wd.print("###END###");
                return true;
            }
            else{
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
            /*wd.println("1");  // indicate only one player mode
            int totalUser = 1;

            
            // determine number of player mode
            if(totalUser == 1){
                // single player mode
                // also need user info
                // username;totalCoins;score;shipskin;x;y;dx;dy;sepcial effects
                wd.println("###user");
                User currentUser = Wave.getInstance().getCurrentUser();
                String info = currentUser.getName() + ";" + currentUser.getCoins() + ";" + currentLevel.getScore() + ";" +  currentLevel.getPlayer().serialize();
                wd.println(info);
            }else{
                // score;shipskin;x;y;dx;dy;special effects
                for(int i = 0; i < totalUser; i++){
                    wd.println("###user");
                    // 
                    // TODO how to record each player info!!!
                }
            }*/

            for(String s: currentLevel.serialization().split("/n")){
                wd.println(s);
            }
            //wd.print(currentLevel.serialization());
            wd.println("ENDL#");
            return true;
        } catch (IOException e) {
            return false;
        }
    }



    // method to load custom levels - RTR
    public void loadCustomLevels() throws IOException {
        var level = new Level();
        for (int i = 0; i < 10; i ++) {
            try (var stream = new FileInputStream("customLevel" + String.valueOf(i) + ".dat");) {
                byte[] levelLength = new byte[4];
                stream.read(levelLength);
                int lengthOfLevel = java.nio.ByteBuffer.wrap(levelLength).order(java.nio.ByteOrder.BIG_ENDIAN).getInt();

                byte[] levelInfoBytes = new byte[lengthOfLevel];
                stream.read(levelInfoBytes);
                String levelInfoString = levelInfoBytes.toString();

                String[] instances = levelInfoString.split("|");
                for (String instance : instances) {
                    String[] instanceInfo = instance.split(",");
                    GameObject object;
                    // enemy entities
                    if (instanceInfo[0].equals("Bouncer")) {
                        object = EnemyObject.create(EnemyTypes.BOUNCER);
                    }
                    else if (instanceInfo[0].equals("Ghost")) {
                        object = EnemyObject.create(EnemyTypes.GHOST);
                    }
                    else if (instanceInfo[0].equals("Tracker")) {
                        object = EnemyObject.create(EnemyTypes.TRACKER);
                    }
                    else if (instanceInfo[0].equals("ShapeShifter")) {
                        object = EnemyObject.create(EnemyTypes.SHAPESHIFTER);
                    }
                    else if (instanceInfo[0].equals("Laser")) {
                        object = EnemyObject.create(EnemyTypes.LASER);
                    }
                    // powerups
                    else if (instanceInfo[0].equals("DestroyShipPowerUp")) {
                        object = new DestroyShip();
                        ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
                    }
                    else if (instanceInfo[0].equals("FreezePowerUp")) {
                        object = new Freeze();
                        ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
                    }
                    else if (instanceInfo[0].equals("HealthPackPowerUp")) {
                        object = new HealthGainSmall();
                        ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
                    }
                    else if (instanceInfo[0].equals("InvincibilityPowerUp")) {
                        object = new TemporaryInvincible();
                        ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
                    }
                    else if (instanceInfo[0].equals("LargeHealthPowerUp")) {
                        object = new HealthGainBig();
                        ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
                    }
                    // obstacles
                    else {
                        object = new Obstacle();
                    }
                    object.setX(Integer.parseInt(instanceInfo[1]));
                    object.setY(Integer.parseInt(instanceInfo[2]));
                    level.getAllObjects().add(object);

                    // add object to corresponding part of the level
                    if (object instanceof PowerUp) {
                        
                        level.getPowerUps().add((PowerUp) object);
                    }
                    else if (object instanceof Obstacle) {
                        level.getObstacles().add((Obstacle) object);
                    }
                    else if (object instanceof EnemyObject) {
                        level.getEnemies().add((EnemyObject) object);
                    }

                }
            }
            catch (IOException e) {
                System.out.println("caught on file exception" + i);
            }
        }
    }

}
