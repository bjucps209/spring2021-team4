package model.GameObjects.Powerups;

import javafx.util.Duration;
import model.Level;
import model.Enums.PowerUps;
import model.GameObjects.Player;

public class TemporaryInvincible extends PowerUp{
  private final static int effectTime = 5;
  private Duration totalPassedTime = new Duration(0);
  public TemporaryInvincible(Level l) {
    super(l);
    this.type = PowerUps.TemporaryInvincible;
    this.effectiveTime = 5;
  }
  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    // Add up all duration
    //
    
  }
  
}
