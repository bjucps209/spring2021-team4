package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

import java.io.*;
import model.Enums.*;

// imports for loadCustomLevel()
import model.GameObjects.*;
import model.GameObjects.Enemies.*;
import model.GameObjects.Powerups.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Wave {

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
    private User currentUser;
    private int coins;

    // Shop variables
    private ShipSkins currentShip;

    // Singleton constructor
    private Wave() {

    }

    public void loadUser() {
        coins = currentUser.getCoins();
    }

    // Starts the game, does all calculations and initializes lists
    public void gameStart() {
        game = new Game(1000, 800);
        game.startHitDetection();
    }

    public void onClosed() {
        game.stopHitDetection();
    }

    // Singleton get instance method
    public static Wave getInstance() {
        if (wave == null) {
            wave = new Wave();
        }

        return wave;
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
    }

    /**
     * This function will load and save all User() in this.users into a file named
     * "userInfo.txt"
     * 
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
     * @param fileName
     * @return
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
     * @param fileName
     * @return
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

    // method to load custom levels - RTR
    public Level loadCustomLevel(String fileName) throws IOException {

        try (var stream = new FileInputStream(fileName + ".dat");) {
            var f = new File(fileName + ".dat");
            int lengthOfLevel = (int) f.length();
            
            var level = new Level();

            byte[] levelInfoBytes = new byte[lengthOfLevel];
            stream.read(levelInfoBytes);
            String levelInfoString = new String(levelInfoBytes);

            // level difficulty in char form
            // char levelDifficulty = levelInfoString.charAt(0);

            levelInfoString = levelInfoString.substring(1);

            String[] instances = levelInfoString.split("\\|");

            for (String instance : instances) {
                String[] instanceInfo = instance.split(Pattern.quote(","));

                GameObject object;
                // enemy entities
                if (instanceInfo[0].equals("Bouncer")) {
                    object = EnemyObject.create(EnemyTypes.BOUNCER, level);
                } else if (instanceInfo[0].equals("Ghost")) {
                    object = EnemyObject.create(EnemyTypes.GHOST, level);
                } else if (instanceInfo[0].equals("Tracker")) {
                    object = EnemyObject.create(EnemyTypes.TRACKER, level);
                } else if (instanceInfo[0].equals("ShapeShifter")) {
                    object = EnemyObject.create(EnemyTypes.SHAPESHIFTER, level);
                } else if (instanceInfo[0].equals("Laser")) {
                    object = EnemyObject.create(EnemyTypes.LASER, level);
                }
                // powerups
                else if (instanceInfo[0].equals("DestroyShip")) {
                    object = PowerUp.create(PowerUps.DestroyShip, level);
                    ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
                } else if (instanceInfo[0].equals("Freeze")) {
                    object = PowerUp.create(PowerUps.Freeze, level);
                    ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
                } else if (instanceInfo[0].equals("HealthGainSmall")) {
                    object = PowerUp.create(PowerUps.HealthGainSmall, level);
                    ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
                } else if (instanceInfo[0].equals("TemporaryInvincible")) {
                    object = PowerUp.create(PowerUps.TemporaryInvincible, level);
                    ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
                } else if (instanceInfo[0].equals("HealthGainBig")) {
                    object = PowerUp.create(PowerUps.HealthGainBig, level);
                    ((PowerUp) object).setAppearTime(Integer.parseInt(instanceInfo[3]));
                } else if (instanceInfo[0].equals("Player")) {
                    object = new Player(level);
                }
                // obstacles
                else {
                    if (instanceInfo[instanceInfo.length - 1].equals("SQUARE")) {
                        object = new Obstacle(ObstacleTypes.SQUARE, level);
                    }
                    else if (instanceInfo[instanceInfo.length - 1].equals("LARGE")) { 
                        object = new Obstacle(ObstacleTypes.LARGE, level);
                    }
                    else if (instanceInfo[instanceInfo.length - 1].equals("NARROW")) {
                        object = new Obstacle(ObstacleTypes.NARROW, level);
                    }
                    else {
                        object = new Obstacle(ObstacleTypes.CORNER, level);
                    }
                }
                object.setX(Integer.parseInt(instanceInfo[1]));
                object.setY(Integer.parseInt(instanceInfo[2]));
                level.getAllObjects().add(object);

                // add object to corresponding part of the level
                if (object instanceof PowerUp) {
                    level.getPowerups().add((PowerUp) object);
                } 
                else if (object instanceof Obstacle) {
                    level.getObstacles().add((Obstacle) object);
                } 
                else if (object instanceof EnemyObject) {
                    level.getEnemies().add((EnemyObject) object);
                } 
                else if (object instanceof Player) {
                    level.setPlayer((Player) object);
                }
            }
            return level;
        } catch (IOException e) {
            var alert = new Alert(AlertType.INFORMATION, "You have not created that level yet.");
            alert.show();
            return null;
        }
    }

    // method to search the directory of Wave for a file with the name supplied in the parameter. if it does exist, return true, if it does not, return false.
    public boolean searchDirectoryForFile(String fileName) {
        // make a file out of this file, get its path, find the path of the directory this file is in, create a file with that directory name, search that directory for the file we want
        File file = new File("Wave.java");
        String basePath = file.getAbsolutePath();
        int dirIndex = basePath.indexOf("Wave.java");
        String dirString = basePath.substring(0, dirIndex);
        File directory = new File(dirString);
        File[] listOfFiles = directory.listFiles();
        for (File f : listOfFiles) {
            if (f.getName().equals(fileName + ".dat")) {
                return true;
            }
        }
        return false;
    }

}
