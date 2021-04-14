package model.GameObjects.Powerups;

import model.Enums.PowerUps;
import model.GameObjects.Player;

public class TemporaryInvincible extends PowerUp{

  public TemporaryInvincible(){
    this.type = PowerUps.TemporaryInvincible;
  }
  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    
  }
  
}
