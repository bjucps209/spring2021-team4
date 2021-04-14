package model.GameObjects.Powerups;

import model.Level;
import model.Enums.PowerUps;
import model.GameObjects.Player;

public class HealthGainBig extends PowerUp {
  private final static int healthAddition = 50;

  public HealthGainBig(Level l){
    super(l);
    this.type = PowerUps.HealthGainBig;
  }



  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    p.setHealth(p.getHealth() + healthAddition);
    this.isFinished = true; // Indicate this powerup is finished of usingz
  }

}
