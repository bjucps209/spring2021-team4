package model.GameObjects.Powerups;

import model.GameObjects.GameObject;
import java.util.Random;
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
      
      int minx = 0;
      int minY = 0;
      int possibleX = 0;
      int possibleY = 0;
      Random ran = new Random();

      do{

        minx 
      }while();
      
      

    }

    public int getAppearTime() {
      return appearTime;
    }
    public void setAppearTime(int appearTime) {
      this.appearTime = appearTime;
    }
}
