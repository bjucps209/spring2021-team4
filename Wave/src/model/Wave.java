//-----------------------------------------------------------
//File:   Wave.java
//Desc:   this file holds references to the user, game, and
//        other attributes of the whole app such as high scores
//-----------------------------------------------------------

package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

import java.io.*;
import model.Enums.*;

// imports for loadCustomLevel()
import model.GameObjects.*;
import model.GameObjects.Enemies.*;
import model.GameObjects.Powerups.*;
import model.GameObjects.Obstacles.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

// wave class holds variables needed when accessing the main menu such as an instance of game and lists of high scores
public class Wave {

    // indicate resume game
    private boolean resumeGame = false;

    // Singleton variable
    private static Wave wave = null;

    // Constant variables
    private final int windowWidth = 0;
    private final int windowHeight = 0;

    // Data variables
    private ArrayList<User> users = new ArrayList<User>(); // A list of exist user
    private ArrayList<HighScoreList> highScores = new ArrayList<HighScoreList>();
    private ArrayList<ShipSkins> skins = new ArrayList<ShipSkins>();

    // Menu variables
    private Game game;

    // User variables
    private User currentUser;  // The current login user
    private IntegerProperty coins = new SimpleIntegerProperty(); // The running score during the game, will be covert to coins later

    // Shop variables
    private ShipSkins currentShip;    // Variable indicate the current shipSkin the player has on at the skin shop

    private DifficultyLevel userChoiceDifficulty = DifficultyLevel.Easy; // Variable indicate the difficulty of the game
    private boolean cheatMode = false;   // Indicate is cheat mode is on or not

    // Singleton constructor
    private Wave() {

    }

    public void loadUser() {
        setCoins(currentUser.getCoins());
    }

    /**
     * loads the game, does all calculations and populates the lists
     * 
     * @param none
     * @return none
     */
    public void gameStart(ArrayList<Level> levels) {
        game = new Game(1000, 800, levels);
        game.setLevels(levels);
        game.initializeDifficulty();
        game.startHitDetection();
    }

    /**
     * method to close the game, stop the game, and save all users
     * 
     * @param none
     * @return none
     */
    public void onClosed() {
        game.stopHitDetection();
        game.stopPlayerHitDetection();
        if (this.getGame().getCurrentLevel().getPlayer().getHealth() > 0) {
            game.save(this.currentUser.getName());
        }

        saveAllUsers();
    }

    // Singleton get instance method
    public static Wave getInstance() {
        if (wave == null) {
            wave = new Wave();
            wave.loadAllUsers();
        }

        return wave;
    }

    /**
     * method to load our list of default levels and return them in an arraylist
     * 
     * @param none
     * @return our list of default levels
     */
    public ArrayList<Level> getDefaultLevels() {
        ArrayList<Level> levels = new ArrayList<Level>();
        for (int i = 0; i < 5; i++) {
            try {
                levels.add(loadCustomLevel("level" + i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return levels;
    }

    public Game getGame() {
        return this.game;
    }

    public int getWindowWidth() {
        return this.windowWidth;
    }

    public int getWindowHeight() {
        return this.windowHeight;
    }

    /**
     * This function take a String userName, check and return User() in this.users
     * that has the same user name
     * 
     * @param userName - String indicate user name
     * @return - user() that has the same name as userName. Or return null for does
     *         not exist
     */
    public User userExist(String userName) {
        // assuming that no two user will holds the same name
        // case sentative
        for (User existUser : this.users) {
            if (existUser.getName().equals(userName)) {
                return existUser;
            }
        }
        // means did not find
        return null;
    }

    // Saves user
    /**
     * The function will add this.currentUser into this.users if does not exist in
     * the list. If exist, will connect the reference of that User() to
     * this.currentUser Condition for exist: one of User() in this.users has the
     * same Name as this.currentUser
     * 
     * @param none
     * @return none
     */
    public void saveCurrentUser() {
        // Simply replace this.user with the same user name in this.users
        User existUser = userExist(currentUser.getName());
        if (existUser != null) {
            // now just change the reference
            this.currentUser = existUser;
        } else {
            // means this is a new user that does not exist in the ArrayList
            this.users.add(this.currentUser);
        }
        // TODO: add coins for different level

        this.currentUser.setCoins(this.coins.get() / 2 + this.currentUser.getCoins());
    }

    /**
     * This function will load and save all User() in this.users into a file named
     * "userInfo.txt"
     * 
     * @param none
     * @return - True if successfully save all User() in this.users into txt file -
     *         False otherwise
     */
    public boolean saveAllUsers() {
        saveCurrentUser(); // First load the current login user into this.users ArrayList
        try (PrintWriter wd = new PrintWriter(new FileWriter("userInfo.txt"))) {

            wd.println(this.users.size());
            for (User existUser : this.users) {
                wd.println(existUser.serialization());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * The method should only be called for the usage of unit testing In order to
     * prevent the overid of userInfo.txt
     * 
     * @param fileName file to be written to
     * @return true if file loaded successfully, false if not
     */
    public boolean saveAllUsersTest(String fileName) {
        saveCurrentUser(); // First load the current login user into the data file
        try (PrintWriter wd = new PrintWriter(new FileWriter(fileName))) {
            wd.println(this.users.size());
            for (User existUser : this.users) {
                wd.println(existUser.serialization());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * This function will try to load information from userInfo.txt into this.user
     * 
     * @param none
     * @return - True if successfully load all information - False if error occurs
     *         during loading informations.
     */
    public boolean loadAllUsers() {
        try (BufferedReader rd = new BufferedReader(new FileReader("userInfo.txt"))) {
            int numberUser = Integer.parseInt(rd.readLine());
            users = new ArrayList<User>();
            for (int i = 0; i < numberUser; i++) {

                String[] information = rd.readLine().split(";");
                User currentUser = new User();

                if (information.length != 4) {
                    // means an error in the information
                    throw new IOException("error in converting file");
                }

                else if (currentUser.deserialization(information) == false) {
                    // means have error in data
                    throw new IOException("error in converting file");
                } else {
                    users.add(currentUser);
                }

            }
            return true;
        } catch (IOException e) {
            // means failed in loading the file
            return false;
        } catch (Exception e) {
            // means error in file
            return false;
        }
    }

    /**
     * This method should only be called and use in case of unit testing!
     * 
     * @param fileName file to be read from
     * @return true if file read successfully, false if not
     */
    public boolean loadAllUsersTest(String fileName) {
        try (BufferedReader rd = new BufferedReader(new FileReader(fileName))) {
            int numberUser = Integer.parseInt(rd.readLine());
            users = new ArrayList<User>();
            for (int i = 0; i < numberUser; i++) {

                String[] information = rd.readLine().split(";");
                User currentUser = new User();

                if (information.length != 4) {
                    // means an error in the information
                    throw new IOException("error in converting file");
                }

                else if (currentUser.deserialization(information) == false) {
                    // means have error in data
                    throw new IOException("error in converting file");
                } else {
                    users.add(currentUser);
                }

            }
            return true;
        } catch (IOException e) {
            // means failed in loading the file
            return false;
        } catch (Exception e) {
            // means error in file
            return false;
        }
    }

    // --- getters ---
    public int getCoins() {
        return coins.get();
    }

    public IntegerProperty coinsProperty() {
        return coins;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<HighScoreList> getHighScores() {
        return this.highScores;
    }

    public ArrayList<ShipSkins> getSkins() {
        return this.skins;
    }

    public ShipSkins getCurrentShip() {
        return this.currentShip;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    // --- setters ---

    public void setCoins(int coins) {
        this.coins.set(coins);
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setHighScores(ArrayList<HighScoreList> highScores) {
        this.highScores = highScores;
    }

    public void setSkins(ArrayList<ShipSkins> skins) {
        this.skins = skins;
    }

    public void setCurrentShip(ShipSkins currentShip) {
        this.currentShip = currentShip;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isResumeGame() {
        return resumeGame;
    }

    public void setResumeGame(boolean resumeGame) {
        this.resumeGame = resumeGame;
    }

    /**
     * searches the current directory for a file, if it exists, it attempts to read
     * it into a level
     * 
     * @param fileName name of the file to be read in
     * @return a level populated with all information read from file
     */
    public Level loadCustomLevel(String fileName) throws IOException {
        if (!fileName.endsWith(".dat")) {
            fileName += ".dat";
        }

        try (var stream = new FileInputStream(fileName);) {
            var f = new File(fileName);
            int lengthOfLevel = (int) f.length();

            var level = new Level();

            byte[] levelInfoBytes = new byte[lengthOfLevel];
            stream.read(levelInfoBytes);
            String levelInfoString = new String(levelInfoBytes);

            String[] instances = levelInfoString.split("\\|");

            for (String instance : instances) {
                String[] instanceInfo = instance.split(Pattern.quote(","));
                GameObject object;
                // enemy entities
                if (instanceInfo.length == 4) {
                    if (instanceInfo[0].equals("Bouncer")) {
                        object = EnemyObject.create(EnemyTypes.BOUNCER, level);
                    } else if (instanceInfo[0].equals("Ghost")) {
                        object = EnemyObject.create(EnemyTypes.GHOST, level);
                    } else if (instanceInfo[0].equals("Tracker")) {
                        object = EnemyObject.create(EnemyTypes.TRACKER, level);
                    }
                    // powerups
                    else if (instanceInfo[0].equals("DestroyShip")) {
                        object = PowerUp.create(PowerUps.DestroyShip, level);
                    } else if (instanceInfo[0].equals("Freeze")) {
                        object = PowerUp.create(PowerUps.Freeze, level);
                    } else if (instanceInfo[0].equals("HealthGainSmall")) {
                        object = PowerUp.create(PowerUps.HealthGainSmall, level);
                    } else if (instanceInfo[0].equals("TemporaryInvincible")) {
                        object = PowerUp.create(PowerUps.TemporaryInvincible, level);
                    } else if (instanceInfo[0].equals("HealthGainBig")) {
                        object = PowerUp.create(PowerUps.HealthGainBig, level);
                    } else if (instanceInfo[0].equals("Player")) {
                        object = new Player(level);
                    }
                    // obstacles
                    else if (instanceInfo[0].equals("Square")) {
                        object = Obstacle.create(ObstacleTypes.SQUARE, level);
                    } else if (instanceInfo[0].equals("Narrow")) {
                        object = Obstacle.create(ObstacleTypes.NARROW, level);
                    }
                    else {
                        object = Obstacle.create(ObstacleTypes.LARGE, level);
                    }
                    object.setAppearTime(Integer.parseInt(instanceInfo[3]));
                    object.setX(Integer.parseInt(instanceInfo[1]));
                    object.setY(Integer.parseInt(instanceInfo[2]));
                    level.getAllObjects().add(object);

                    // add object to corresponding part of the level
                    if (object instanceof PowerUp) {
                        level.getPowerUps().add((PowerUp) object);
                    } else if (object instanceof Obstacle) {
                        level.getObstacles().add((Obstacle) object);
                    } else if (object instanceof EnemyObject) {
                        level.getEnemies().add((EnemyObject) object);
                    } else if (object instanceof Player) {
                        level.setPlayer((Player) object);
                    }
                }
            }
            return level;
        } catch (IOException e) {
            System.out.println(fileName);
            return null;
        }
    }

    /**
     * searches through the current directory to ensure a file with the name
     * represented by fileName exists
     * 
     * @param fileName file to be searched for in the current directory
     * @return true if the file exists, false if it doe snot exist
     */
    public boolean searchDirectoryForFile(String fileName) {

        // some ideas from:
        // https://stackoverflow.com/questions/15624226/java-search-for-files-in-a-directory
        File file = new File("Wave.java");
        String basePath = file.getAbsolutePath();
        int dirIndex = basePath.indexOf("Wave.java");
        String dirString = basePath.substring(0, dirIndex);

        File directory = new File(dirString);
        File[] listOfFiles = directory.listFiles();
        for (File f : listOfFiles) {
            if (f.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    public DifficultyLevel getUserChoiceDifficulty() {
        return userChoiceDifficulty;
    }

    public void setUserChoiceDifficulty(DifficultyLevel userChoiceDifficulty) {
        this.userChoiceDifficulty = userChoiceDifficulty;
    }

    public boolean isCheatMode() {
        return cheatMode;
    }

    public void setCheatMode(boolean cheatMode) {
        this.cheatMode = cheatMode;
    }
}
