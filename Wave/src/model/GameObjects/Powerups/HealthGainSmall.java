package model.GameObjects.Powerups;

import model.Level;
import model.Enums.PowerUps;
import model.GameObjects.Player;

public class HealthGainSmall extends PowerUp {
  // Note: this implement the small gain package in the game specification
  private final static int healthAddition = 10;

  public HealthGainSmall(Level l) {
    super(l);
    this.type = PowerUps.HealthGainSmall;
    this.effectiveTime = 0;
  
  }

  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    if (this.isFinished == false) {
      p.setHealth(p.getHealth() + healthAddition);
      this.isFinished = true;
    }

  }

}
