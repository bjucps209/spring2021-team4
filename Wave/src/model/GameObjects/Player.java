package model.GameObjects;

import model.Enums.ShipSkins;

public class Player extends GameObject {
    // contains info for a Player during the game
    
    // instance variables
    private int health;
    private int speed = 5; // speed that dx and dy should be (0 or whatever speed is)
    private ShipSkins currentShipSkins; 

    public Player() {
        super();
        x = 40;
        y = 5;
        dx = 0;
        dy = 0;
        width = 5;
        height = 5;
    }

    public void moveUp() {
        this.dy = -speed;
    }

    public void moveDown() {
        this.dy = speed;
    }

    public void moveLeft() {
        this.dx = -speed;
    }

    public void moveRight() {
        this.dx = speed;
    }

    public void moveNeutral() {
        this.dx = 0;
        this.dy = 0;
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
        return health+";"+currentShipSkins.toString()+";"+ x+";"+y+";"+width+";"+height+";"+dx+";"+dy;
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
        this.x = Integer.parseInt(restInfo[2]);
        this.y = Integer.parseInt(restInfo[3]);
        this.width = Integer.parseInt(restInfo[4]);
        this.height = Integer.parseInt(restInfo[5]);
        this.dx = Integer.parseInt(restInfo[6]);
        this.dy = Integer.parseInt(restInfo[7]);
        // TODO: handle rest of special affect on player
        return true;
        } catch (Exception e){
            // means error in converting
            return false;
        }
    }



}
