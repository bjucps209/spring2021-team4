package model.GameObjects.Powerups;

import javafx.util.Duration;
import model.Enums.PowerUps;
import model.GameObjects.Player;

public class Freeze extends PowerUp{
  private final static int existTime = 5;
  private Duration totalPassedTime = new Duration(0);

  public Freeze(){
    this.type = PowerUps.Freeze;
  }
  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    
  }
  
}
