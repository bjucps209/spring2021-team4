package model;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.io.*;
import model.Enums.EnemyTypes;
import model.Enums.PowerUps;
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

    public void stopHitDetection() {
        for (GameObject g : levels.get(levelNum).getAllObjects()) {
            try {
                g.stopHitDetection();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
            //TODO: need to call and load all level from level editor first
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



    // // method to load custom levels - RTR
    // public void loadCustomLevel(String fileName) throws IOException {

    //     try (var stream = new FileInputStream(fileName + ".dat");) {
    //         var f = new File(fileName + ".dat");
    //         int lengthOfLevel = (int) f.length();
    //         var level = new Level();

    //         byte[] levelInfoBytes = new byte[lengthOfLevel];
    //         stream.read(levelInfoBytes);
    //         String levelInfoString = new String(levelInfoBytes);

    //         // level difficulty in char form
    //         char levelDifficulty = levelInfoString.charAt(0);

    //         levelInfoString = levelInfoString.substring(1);

    //         String[] instances = levelInfoString.split("\\|");
            
    //         for (String instance : instances) {
    //             String[] instanceInfo = instance.split(Pattern.quote(","));
                
    //             GameObject object;
    //             // enemy entities
    //             if (instanceInfo[0].equals("Bouncer")) {
    //                 object = EnemyObject.create(EnemyTypes.BOUNCER, level);
    //             }
    //             else if (instanceInfo[0].equals("Ghost")) {
    //                 object = EnemyObject.create(EnemyTypes.GHOST, level);
    //             }
    //             else if (instanceInfo[0].equals("Tracker")) {
    //                 object = EnemyObject.create(EnemyTypes.TRACKER, level);
    //             }
    //             else if (instanceInfo[0].equals("ShapeShifter")) {
    //                 object = EnemyObject.create(EnemyTypes.SHAPESHIFTER, level);
    //             }
    //             else if (instanceInfo[0].equals("Laser")) {
    //                 object = EnemyObject.create(EnemyTypes.LASER, level);
    //             }
    //             // powerups
    //             else if (instanceInfo[0].equals("DestroyShip")) {
    //                 object = PowerUp.create(PowerUps.DestroyShip, level);
    //                 ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
    //             }
    //             else if (instanceInfo[0].equals("Freeze")) {
    //                 object = PowerUp.create(PowerUps.Freeze, level);
    //                 ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
    //             }
    //             else if (instanceInfo[0].equals("HealthGainSmall")) {
    //                 object = PowerUp.create(PowerUps.HealthGainSmall, level);
    //                 ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
    //             }
    //             else if (instanceInfo[0].equals("TemporaryInvincible")) {
    //                 object = PowerUp.create(PowerUps.TemporaryInvincible, level);
    //                 ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
    //             }
    //             else if (instanceInfo[0].equals("HealthGainBig")) {
    //                 object = PowerUp.create(PowerUps.HealthGainBig, level);
    //                 ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
    //             }
    //             else if (instanceInfo[0].equals("Player")) {
    //                 object = new Player(level);
    //             }
    //             // obstacles
    //             else {
    //                 object = new Obstacle(level);
    //             }
    //             object.setX(Integer.parseInt(instanceInfo[1]));
    //             object.setY(Integer.parseInt(instanceInfo[2]));
    //             level.getAllObjects().add(object);

    //             // add object to corresponding part of the level
    //             if (object instanceof PowerUp) {
                    
    //                 level.getPowerups().add((PowerUp) object);
    //             }
    //             else if (object instanceof Obstacle) {
    //                 level.getObstacles().add((Obstacle) object);
    //             }
    //             else if (object instanceof EnemyObject) {
    //                 level.getEnemies().add((EnemyObject) object);
    //             }
    //         }
    //         // adds the level and all 
    //         levels.add(level);
    //         // potentially launch the list of levels when this method is called?
    //         // or create another method to launch the game
            
    //     }
    //     catch (IOException e) {
    //         System.out.println("caught on file exception \nFileName:");
    //     }
    // }

}
