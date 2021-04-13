package model.GameObjects;

import model.Wave;
import model.Enums.ShipSkins;

public class Player extends GameObject {
    // contains info for a Player during the game
    
    // instance variables
    private int health;
    private int speed = 5; // speed that dx and dy should be (0 or whatever speed is)
    private ShipSkins currentShipSkins; 

    public Player() {
        super();
        setX(5);
        setY(5);
        setDx(0);
        setDy(0);
        setWidth(50);
        setHeight(50);
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
      return health;
    }
    public ShipSkins getCurrentShipSkins() {
      return currentShipSkins;
    }

    // --- setters ---
    public void setHealth(int health) {
      this.health = health;
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
        health = Integer.parseInt(restInfo[0]);
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
