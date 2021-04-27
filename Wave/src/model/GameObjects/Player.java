//-----------------------------------------------------------
//File:   Player.java
//Desc:   file that represents the player. attributes include
//        currentskin, health, speed, invincibility, and winstate
//-----------------------------------------------------------

package model.GameObjects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Level;
import model.Wave;
import model.Enums.PowerUps;
import model.Enums.ShipSkins;
import model.GameObjects.Powerups.Freeze;
import model.GameObjects.Powerups.PowerUp;
import model.GameObjects.Powerups.TemporaryInvincible;
import model.GameObjects.SpeedPanels.SpeedPanel;

public class Player extends GameObject {
    // contains info for a Player during the game

    // instance variables
    private IntegerProperty health = new SimpleIntegerProperty(100);
    private static int speed = 5; // speed that dx and dy should be (0 or whatever speed is)
    private ShipSkins currentShipSkins;
    // private ArrayList<GameObject> activatedAffects = new ArrayList<>();
    private boolean temporaryInvincible;
    public boolean winState = false;

    public boolean getTemporaryInvincible() {
        return this.temporaryInvincible;
    }


    public boolean isWinState() {
        return this.winState;
    }

    public boolean getWinState() {
        return this.winState;
    }

    public void setWinState(boolean winState) {
        this.winState = winState;
    }

    public boolean isMoveOn() {
        return this.moveOn;
    }

    public boolean getMoveOn() {
        return this.moveOn;
    }

    public void setMoveOn(boolean moveOn) {
        this.moveOn = moveOn;
    }
    public boolean moveOn = false;
    

    /**
     * creates a new thread for the attribute hit detection
     * @param none
     * @return a new hit detection thread
     */
    public Thread changeThread(){
        return hitDetection = new Thread(() -> {
            while (true) {
                checkWallCollision();
                var collisionEnemy = checkCollision(currentLevel.getEnemies());
                if (currentLevel.getEnemies() != null && collisionEnemy != null) {
                    hits.add(collisionEnemy);
                } 
                
                //System.out.println(currentLevel.getObstacles());
                var collisionObstacle = checkCollision(currentLevel.getObstacles());
                if (currentLevel.getObstacles() != null && collisionObstacle!= null) {
                    hits.add(collisionObstacle);
                }

                var collisionPowerUp = checkCollision(currentLevel.getPowerUps());
                if (currentLevel.getPowerUps()!= null && collisionPowerUp != null ) {
                    hits.add(collisionPowerUp);
                    this.currentLevel.getPowerUps().remove(collisionPowerUp);
                    //this.currentLevel.getAllObjects().remove(collisionPowerUp);
                    
                    
                }
                int i = 0;
                while (hits.size() != 0 && i < hits.size()) {

                    
                    if( processHit(hits.get(0), this, this)){
                        if(hits.get(0) instanceof PowerUp){
                            //this.currentLevel.getPowerUps().remove(hits.get(0));
                            this.currentLevel.getAllObjects().remove(hits.get(0));
                        }
                        hits.remove(hits.get(0)); 
                    }
                    //processHit(hits.get(0), this, this);
                    
                    hits.removeIf( (GameObject o) -> (o instanceof PowerUp) == false ); 
                   i++;
                }
                
                //for(GameObject)

                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Player(Level l) {
        super(l);
        setX(500);
        setY(500);
        setDx(0);
        setDy(0);
        setWidth(50);
        setHeight(50);
        try {
            this.currentShipSkins = Wave.getInstance().getCurrentUser().getShip();
        }
        catch (NullPointerException e) {
            this.currentShipSkins = ShipSkins.SHIP1;
        }
        hitDetection = new Thread(() -> {
            while (true) {
                checkWallCollision();
                var collisionEnemy = checkCollision(currentLevel.getEnemies());
                if (currentLevel.getEnemies() != null && collisionEnemy != null) {
                    hits.add(collisionEnemy);
                } 
                
                //System.out.println(currentLevel.getObstacles());
                var collisionObstacle = checkCollision(currentLevel.getObstacles());
                if (currentLevel.getObstacles() != null && collisionObstacle!= null) {
                    hits.add(collisionObstacle);
                }

                var collisionPowerUp = checkCollision(currentLevel.getPowerUps());
                if (currentLevel.getPowerUps()!= null && collisionPowerUp != null ) {
                    hits.add(collisionPowerUp);
                    this.currentLevel.getPowerUps().remove(collisionPowerUp);

                    // TODO remove GUI
                    //this.currentLevel.getAllObjects().remove(collisionPowerUp);
                    
                    
                }

                var speedPanel = checkCollision(currentLevel.getSpeedPanels());
                if(currentLevel.getSpeedPanels() != null && speedPanel != null ){
                    this.hits.removeIf( (GameObject o) -> o instanceof SpeedPanel);// remove all other speedPanels in affect
                    this.hits.add(speedPanel);
                }
                int i = 0;
                while (hits.size() != 0 && i < hits.size()) {

                    
                    if( processHit(hits.get(0), this, this)){
                        if(hits.get(0) instanceof PowerUp){
                            //this.currentLevel.getPowerUps().remove(hits.get(0));
                            this.currentLevel.getAllObjects().remove(hits.get(0));
                        }
                        hits.remove(hits.get(0)); 
                    }
                    //processHit(hits.get(0), this, this);
                    
                    hits.removeIf( (GameObject o) -> ((o instanceof PowerUp) == false ) ); 
                   i++;
                }
                
                //for(GameObject)

                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * method that stops wall hit detection
     * @param none
     * @return none
     */
    public void changeWallDetection() {
        hitDetection.stop();
    }

    /**
     * method that checks if the player has run into a wall
     * @param none
     * @return none
     */
    @Override
    public void checkWallCollision() {
        if (winState) {
            if (getX() <= 0 || getX() >= Wave.getInstance().getGame().getGameWidth() - getWidth() - 20) {
                dx.set(0);
                if (getX() < 10) {
                    x.set(1);
                } else {
                    this.moveOn = true;
                }
            } else if (getY() <= 0 || getY() >= Wave.getInstance().getGame().getGameHeight() - getHeight() - 9) {
                dy.set(0);
                if (getY() < 10) {
                    y.set(1);
                } else {
                    y.set(Wave.getInstance().getGame().getGameHeight() - getHeight() - 10);
                }
            }
        } else {
            if (getX() <= 0 || getX() >= Wave.getInstance().getGame().getGameWidth() - getWidth() - 20) {
                dx.set(0);
                if (getX() < 10) {
                    x.set(1);
                } else {
                    x.set(Wave.getInstance().getGame().getGameWidth() - getWidth() - 19);
                }
            } else if (getY() <= 0 || getY() >= Wave.getInstance().getGame().getGameHeight() - getHeight() - 9) {
                dy.set(0);
                if (getY() < 10) {
                    y.set(1);
                } else {
                    y.set(Wave.getInstance().getGame().getGameHeight() - getHeight() - 10);
                }
            }
        }
    }

    /**
     * method through which the player takes damage
     * @param damage the value for which the player is damaged
     * @return none
     */
    public void takeDamage(int damage){

        if(this.temporaryInvincible == false){
            this.health.set(this.health.get() - damage);
        }
        
    }
    public void moveUp() {
        setDy(-speed);
    }

    public void moveDown() {
        setDy(speed);
    }

    public void moveLeft() {
        setDx(-speed);
    }

    public void moveRight() {
        setDx(speed);
    }

    public void moveNeutral() {
        setDx(0);
        setDy(0);
    }

    // --- getters ---
    public int getHealth() {
        return this.health.get();
    }

    public ShipSkins getCurrentShipSkins() {
        return currentShipSkins;
    }

    public IntegerProperty healthProperty() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    /*
     * public ArrayList<GameObject> getActivatedAffects() { return
     * this.activatedAffects; }
     */

    public boolean isTemporaryInvincible() {
        return temporaryInvincible;
    }

    // --- setters ---
    public void setHealth(int health) {
        this.health.set(health);
    }

    public void setCurrentShipSkins(ShipSkins currentShipSkins) {
        this.currentShipSkins = currentShipSkins;
    }

    public void setHealth(IntegerProperty health) {
        this.health = health;
    }

    public static void setSpeed(int speed) {
        Player.speed = speed;
    }

    /*
     * public void setActivatedAffects(ArrayList<GameObject> activatedPowerUs) {
     * this.activatedAffects = activatedPowerUs; }
     */

    public void setTemporaryInvincible(boolean temporaryInvincible) {
        this.temporaryInvincible = temporaryInvincible;
    }

    @Override
    public String serialize() {
        // TODO Auto-generated method stub
        // TODO still needs to add in special effects !!!
        String returns = health.get() + ";" + currentShipSkins.toString() + ";" + x.get() + ";" + y.get() + ";" + width.get()
                + ";" + height.get() + ";" + dx.get() + ";" + dy.get();

        for (GameObject affObject : this.hits) {
            // need to give its affect type, enum type, remaining time
            String data = "";
            if (affObject instanceof PowerUp) {
                PowerUp ob = (PowerUp) affObject;
                data += "PowerUp," + ob.getType() + "," + ob.getEffectiveTime() + "," + ob.getPassedTime()+","+ob.getStartTime();

            } else {
                // should be a panel
            }
            returns += ";" + data;
        }

        return returns;
    }

    @Override
    public boolean deserialize(String info) {
        // TODO Auto-generated method stub
        // info contains
        // x,y,width,height,dx,dy,special effects
        try {
            String[] restInfo = info.split(";");
            health.set(Integer.parseInt(restInfo[0]));
            currentShipSkins = ShipSkins.valueOf(restInfo[1]);

            this.x.set(Integer.parseInt(restInfo[2]));
            this.y.set(Integer.parseInt(restInfo[3]));
            this.width.set(Integer.parseInt(restInfo[4]));
            this.height.set(Integer.parseInt(restInfo[5]));
            this.dx.set(Integer.parseInt(restInfo[6]));
            this.dy.set(Integer.parseInt(restInfo[7]));
            // TODO: handle rest of special affect on player

            if (restInfo.length != 8) {
                for (int i = 8; i < restInfo.length; i++) {
                    String[] data = restInfo[i].split(",");
                    if (data[0].equals("PowerUp")) {
                        // only should have
                        PowerUps type = PowerUps.valueOf(data[1]);
                        if (type == PowerUps.Freeze) {
                            Freeze pow = new Freeze(this.currentLevel);
                            pow.setEffectiveTime(Integer.parseInt(data[2]));
                            pow.setPassedTime(Integer.parseInt(data[3]));
                            pow.setStartTime(Integer.parseInt(data[4]));
                            this.hits.add(pow);
                            this.currentLevel.getAllObjects().add(pow);
                        } else if (type == PowerUps.TemporaryInvincible) {
                            TemporaryInvincible pow = new TemporaryInvincible(this.currentLevel);
                            pow.setEffectiveTime(Integer.parseInt(data[2]));
                            pow.setPassedTime(Integer.parseInt(data[3]));
                            pow.setStartTime(Integer.parseInt(data[4]));
                            this.hits.add(pow);
                            this.currentLevel.getAllObjects().add(pow);
                        } else {
                            // should not be the case
                            return false;
                        }
                    } else {
                        // TODO: panel effect for speed up and down
                    }
                }
            }



          this.hitDetection = changeThread();
     
            return true;
        } catch (Exception e) {
            // means error in converting
            return false;
        }
    }

}
