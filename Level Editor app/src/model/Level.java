package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Enums.EnemyTypes;
import model.Enums.ObstacleTypes;
import model.Enums.ShipSkins;
import model.GameObjects.GameObject;
import model.GameObjects.Obstacles.*;
import model.GameObjects.Player;
import model.GameObjects.Enemies.Bouncer;
import model.GameObjects.Enemies.EnemyObject;
import model.GameObjects.Enemies.Ghost;
import model.GameObjects.Enemies.Tracker;
import model.GameObjects.Powerups.PowerUp;

public class Level {
    // Class that holds all info for 1 level

    // Object lists
    private ArrayList<GameObject> allObjects = new ArrayList<GameObject>();
    private ArrayList<EnemyObject> enemies = new ArrayList<EnemyObject>();
    private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    private ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();
    
    // Data info
    private int score;

    // Gameplay variables
    private Player player;

    private int remainingTime; // possible connect to TimeLine() with data binding technique

    // This construction should only be use in unit test

    public Level() {
        
    }

    // start all info necessary for level
    public void initialize() {
        spawnPlayer();
        spawnEnemies();
    }

    

    // spawns player
    public void spawnPlayer() {
        player = new Player();
        allObjects.add(player);
    }

    // spawns enemies
    public void spawnEnemies() {
        spawnEnemy(EnemyTypes.BOUNCER, 40, 40);
    }

    // creates obstacles
    public void spawnObstacles() {

    }

    public void spawnPowerups() {
        
    }

    // Takes the enemy type, x, and y. creates an enemy
    // and adds it to enemy list and all object list
    public void spawnEnemy(EnemyTypes type, int x, int y) {
        EnemyObject e = EnemyObject.create(type);
        int speed = calcEnemySpeed(type);
        e.setX(x);
        e.setY(y);
        e.setDx(speed);
        e.setDy(speed);
        e.setWidth(50);
        e.setHeight(50);
        enemies.add(e);
        allObjects.add(e);
    }

    public int calcEnemySpeed(EnemyTypes type) {
        switch (type) {
            case BOUNCER:
                return Bouncer.speed;
            case GHOST:
                return Ghost.speed;
            case TRACKER:
                return Tracker.speed;
            default:
                return 0;
            }
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

    public void setPowerups(ArrayList<PowerUp> list) {
        this.powerups = list;
    }

    public ArrayList<PowerUp> getPowerups() {
        return this.powerups;
    }

    /**
     * The function retursn a String consist of information about this level()
     * @return - String represent the data of leve() 
     */
    public String serialization() {
        String info = "";

        // TODO: detect in more than one player mode
        int numberPlayer = 1;
        info += numberPlayer + "/n";

        if (numberPlayer == 1) {
            // single player mode
            // also need user info
            // username;totalCoins;score;health;shipskin;x;y;dx;dy;sepcial effects
            User currentUser = Wave.getInstance().getCurrentUser();
            info += "###user/n";
            info += currentUser.getName() + ";" + currentUser.getCoins() + ";" + score + ";" + player.serialize()
                    + "/n";

        } else {
            for (int i = 0; i < numberPlayer; i++) {
                info += "###user/n";
                // TODO : how to handle multiple player
            }
        }

        info += allObjects.size()-numberPlayer + "/n";  //TODO: note allObject also contains player
        for (GameObject object : allObjects) {
            if(object instanceof Player){
                continue;
            }
            info += "###gameobject/n";
            if (object instanceof EnemyObject) {
                object = (EnemyObject) object;
                info += object.serialize() + "/n";
            } else if (object instanceof Obstacle) {
                object = (Obstacle) object;
                info += object.serialize() + "/n";
            } else if (object instanceof PowerUp) {
                object = (PowerUp) object;
                info += object.serialize() + "/n";
            }else{
                // means this object is a player
            }

        }

        return info;
    }

    /**
     * The function loads Level's information from the file.
     * @param rd- a BufferedReader that opens the file which contains data
     * @return True if successfully load the information of this particular level into Leve(), false otherwise
     * @throws IOException
     */
    public boolean deserialization(BufferedReader rd) throws IOException {

        // TODO: handle difficulty level
        // String difficultyLevel = rd.readLine();

        int totalPlayer = Integer.parseInt(rd.readLine());
        if (totalPlayer == 1) {
            String headString = rd.readLine();
            if (headString.equals("###user") == false) {
                // error in format
                throw new IOException("Error in loading user");
            }

            String[] userInfo = rd.readLine().split(";");
            Wave.getInstance().loadAllUsers();
            Wave.getInstance().setCurrentUser(new User(userInfo[0]));
            Wave.getInstance().saveCurrentUser(); // connect currentUser with user that holds the same name in the data
            Wave.getInstance().getCurrentUser().setCoins(Integer.parseInt(userInfo[1])); // set coins
            Wave.getInstance().getCurrentUser().setShip(ShipSkins.valueOf(userInfo[4]));
            score = Integer.parseInt(userInfo[2]); // set score

            String restInfo = "";

            for (int i = 3; i < userInfo.length; i++) {
                restInfo += userInfo[i];
                if (i + 1 < userInfo.length) {
                    restInfo += ";";
                }
            }

            if (player.deserialize(restInfo) == false) {
                throw new IOException("Error in converting user data");
                // means error in converting user data
            }else{
                // add player into the list
                allObjects.add(player);
            }

        } else {
            // TODO: handle multiple player mode
        }

        String nextLine = rd.readLine();
     
            int totalGameObject = Integer.parseInt(nextLine); // a line indicate number of gameObject
            for (int i = 0; i < totalGameObject; i++) {

                String headerLine = rd.readLine();
                if (headerLine.equals("###gameobject") == false) {
                    throw new IOException("wrong format of gameobject");
                }

                String info = rd.readLine();
                String gameObjectInfo[] = info.split(";");
                String restInfo = ""; // String that holds rest of information for each object's deserialization
                for (int k = 2; k < gameObjectInfo.length; k++) {
                    restInfo += gameObjectInfo[k];
                    if (k + 1 < gameObjectInfo.length) {
                        restInfo += ";";
                    }
                }

                String object = gameObjectInfo[0];
                String type = gameObjectInfo[1];

                if (object.equals("EnemyObject")) {
                    // TODO: multiple other kind of enemy
                    EnemyObject enemy = new EnemyObject() {
                    }; // the reference will change depent on different enemy later
                    if (EnemyTypes.valueOf(type) == EnemyTypes.BOUNCER) {
                        enemy = new Bouncer();
                        enemy.setType(EnemyTypes.valueOf(type)); // set enemy type value

                    }

                    if (enemy.deserialize(restInfo) == false) {
                        throw new IOException("error in converting enemy data");
                    } else {
                        // add enemy into list
                        allObjects.add(enemy);
                        enemies.add(enemy);
                    }

                } else if (object.equals("Obstacle")) {
                    // TODO have to determine if obstalce will have different type or not

                    // assumenot in this calse
                    Obstacle obstacle = Obstacle.create(ObstacleTypes.SQUARE);
                    if (obstacle.deserialize(restInfo) == false) {
                        throw new IOException("Error in converting obstacle data");
                    } else {
                        allObjects.add(obstacle);
                        obstacles.add(obstacle);
                    }
                } else if (object.equals("PowerUp")) {
                    // TODO: power up shold have different type
                    // TODO: in beta version
                } else {
                    // contains a type that does not exist
                    throw new IOException("Object has a type does not exist");
                }
            }
        

        return true;

    }
}
