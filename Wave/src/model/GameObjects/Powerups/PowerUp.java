package model.GameObjects.Powerups;

import model.GameObjects.GameObject;
import model.GameObjects.Player;

import java.util.ArrayList;
import java.util.Random;
public abstract class PowerUp extends GameObject {

    //Note: powerups will be position by the level editors.
    protected int appearTime;
    protected PowerUp type;
    
    public abstract void collisionWithPlayer(Player p);
    @Override
    public void update() {
      // powerups will not move during the game
    }
    @Override
    public String serialize(){
      // TODO: type for different power ups
      return "PowerUp;";// TODO: type, x,y,dx,dy
    }

    public boolean deserialize(String info){
      //TODO: deserialize it later
      return true;
    }


    

    public int getAppearTime() {
      return appearTime;
    }
    public void setAppearTime(int appearTime) {
      this.appearTime = appearTime;
    }
    public PowerUp getType() {
      return type;
    }
    public void setType(PowerUp type) {
      this.type = type;
    }
}
