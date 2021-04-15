package model.GameObjects.Powerups;

import model.Enums.PowerUps;
import model.GameObjects.Player;

public class HealthGainBig extends PowerUp {
  private final static int healthAddition = 50;

  public HealthGainBig(){
    this.type = PowerUps.HealthGainBig;
    this.effectiveTime = 0;
  }



  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    p.setHealth(p.getHealth() + healthAddition);
    this.isFinished = true; // Indicate this powerup is finished of usingz
  }

}
