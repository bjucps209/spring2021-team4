package model.GameObjects.Powerups;

import javafx.util.Duration;
import model.Enums.PowerUps;
import model.GameObjects.Player;

public class TemporaryInvincible extends PowerUp{
  private final static int effectTime = 5;
  private Duration totalPassedTime = new Duration(0);
  public TemporaryInvincible(){
    this.type = PowerUps.TemporaryInvincible;
  }
  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    // Add up all duration
    //
    
  }
  
}
