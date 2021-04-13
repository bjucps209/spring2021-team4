package model.GameObjects.Powerups;

import model.GameObjects.GameObject;

public abstract class PowerUp extends GameObject {

    protected int appearTime;
    
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

    public void randomlyPlacePowerUps(){
      int width = 1000;
      int height = 800; 
      
      
    }

    public int getAppearTime() {
      return appearTime;
    }
    public void setAppearTime(int appearTime) {
      this.appearTime = appearTime;
    }
}
