package model.GameObjects;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import model.Wave;
import model.Enums.ShipSkins;
import model.GameObjects.Enemies.EnemyObject;

public class Player extends GameObject {
    // contains info for a Player during the game
    
    // instance variables
    private IntegerProperty health = new SimpleIntegerProperty(100);
    private int speed = 5; // speed that dx and dy should be (0 or whatever speed is)
    private ShipSkins currentShipSkins; 

    public Player() {
        super();
        setX(500);
        setY(500);
        setDx(0);
        setDy(0);
        setWidth(50);
        setHeight(50);
    }

    @Override
    public void update() {
        collisionNum++;
        if (collisionNum % 50 == 0) {
            checkEnemyCollision();
            checkWallCollision();
            checkObstacleCollision(); 
        } 
        x.set(getX() + getDx());
        y.set(getY() + getDy());
    } 

    @Override
    public void checkWallCollision() {
        if (getX() <= 0 || getX() >= Wave.getInstance().getGame().getGameWidth() - getWidth()) {
            dx.set(0);
            if (getX() < 10) {
                x.set(1);
            } else {
                x.set(Wave.getInstance().getGame().getGameWidth() - getWidth() - 1);
            }
        } else if (getY() <= 0 || getY() >= Wave.getInstance().getGame().getGameHeight() - getHeight()) {
            dy.set(0);
            if (getY() < 10) {
                y.set(1);
            } else {
                y.set(Wave.getInstance().getGame().getGameHeight() - getHeight() - 1);
            }
        }
    }

    public void checkEnemyCollision() {
        enemies = Wave.getInstance().getGame().getCurrentLevel().getEnemies();
        for (EnemyObject e : enemies) {
            for (int i = e.getX(); i <= e.getX() + e.getWidth(); i++) {
                for (int k = getX(); k <= getX() + getWidth(); k++) {
                    if (k == i) {
                        for (int j = e.getY(); j <= e.getY() + e.getHeight(); j++) {
                            for (int l = getY(); l <= getY() + getHeight(); l++) {
                                if (l == j) {
                                    //TODO HIT
                                    health.set(health.get() - 1);
                                }
                            }
                        }
                    }
                }
            }
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

    // --- setters ---
    public void setHealth(int health) {
      this.health.set(health);
    }

    public void setCurrentShipSkins(ShipSkins currentShipSkins) {
      this.currentShipSkins = currentShipSkins;
    }

    @Override
    public String serialize() {
        // TODO Auto-generated method stub
        // TODO still needs to add in special effects !!!
        return health+";"+currentShipSkins.toString()+";"+ x.get()+";"+y.get()+";"+width.get()+";"+height.get()+";"+dx.get()+";"+dy.get();
    }

    @Override
    public boolean deserialize(String info) {
        // TODO Auto-generated method stub
        // info contains
        // x,y,width,height,dx,dy,special effects
        try{
        String[] restInfo = info.split(";");
        health.set(Integer.parseInt(restInfo[0]));
        currentShipSkins = ShipSkins.valueOf(restInfo[1]);
        
        this.x.set (Integer.parseInt(restInfo[2]));
        this.y.set(Integer.parseInt(restInfo[3]));
        this.width.set(Integer.parseInt(restInfo[4]));
        this.height.set(Integer.parseInt(restInfo[5]));
        this.dx.set(Integer.parseInt(restInfo[6]));
        this.dy.set(Integer.parseInt(restInfo[7]));
        // TODO: handle rest of special affect on player
        return true;
        } catch (Exception e){
            // means error in converting
            return false;
        }
    }



}
