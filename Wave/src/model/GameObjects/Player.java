package model.GameObjects;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import model.Level;
import model.Enums.PowerUps;
import model.Enums.ShipSkins;
import model.GameObjects.Powerups.Freeze;
import model.GameObjects.Powerups.PowerUp;
import model.GameObjects.Powerups.TemporaryInvincible;

public class Player extends GameObject {
    // contains info for a Player during the game

    // instance variables
    private IntegerProperty health = new SimpleIntegerProperty(100);
    private int speed = 5; // speed that dx and dy should be (0 or whatever speed is)
    private ShipSkins currentShipSkins;
    // private ArrayList<GameObject> activatedAffects = new ArrayList<>();
    private boolean temporaryInvincible;

    public Player(Level l) {
        super(l);
        setX(500);
        setY(500);
        setDx(0);
        setDy(0);
        setWidth(50);
        setHeight(50);
        hitDetection = new Thread(() -> {
            while (true) {
                ArrayList<Boolean> isFinished = new ArrayList<>();
                for (GameObject object : this.hits) {
                    isFinished.add(processHit(object, this));
                    // store true if should be delete, store false other wise
    
                }
                int i = 0;
                for (boolean value : isFinished) {
                    if (value) {
                        this.hits.remove(i);
                    }
                    i++;
                }
    
                checkWallCollision();
                if (checkCollision(currentLevel.getEnemies()) != null) {
                    hits.add(checkCollision(currentLevel.getEnemies()));
                }
    
                PowerUp hitPowerUp = (PowerUp) checkCollision(currentLevel.getPowerUps());
                if (hitPowerUp != null) {
                    hits.add(hitPowerUp); // TODO: potentially cause problem in delay powerup's reaction?
                }
                


                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

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

    public void setSpeed(int speed) {
        this.speed = speed;
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
                data += "PowerUp," + ob.getType() + "," + ob.getEffectiveTime() + "," + ob.getPassedTime()+","+ob.getAppearTime();

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
                        } else if (type == PowerUps.TemporaryInvincible) {
                            TemporaryInvincible pow = new TemporaryInvincible(this.currentLevel);
                            pow.setEffectiveTime(Integer.parseInt(data[2]));
                            pow.setPassedTime(Integer.parseInt(data[3]));
                            pow.setStartTime(Integer.parseInt(data[4]));
                            this.hits.add(pow);
                        } else {
                            // should not be the case
                            return false;
                        }
                    } else {
                        // TODO: panel effect for speed up and down
                    }
                }
            }

            return true;
        } catch (Exception e) {
            // means error in converting
            return false;
        }
    }

}
