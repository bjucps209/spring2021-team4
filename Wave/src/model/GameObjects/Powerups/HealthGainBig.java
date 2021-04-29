//-----------------------------------------------------------
//File:   HealthGainBig.java
//Desc:   powerup that gives the playe 50 health
//        
//-----------------------------------------------------------

package model.GameObjects.Powerups;

import model.Level;
import model.Enums.PowerUps;
import model.GameObjects.Player;

// gives the player 50 health when hit by the user
public class HealthGainBig extends PowerUp {
  private final static int healthAddition = 50;

  public HealthGainBig(Level l) {
    super(l);
    this.type = PowerUps.HealthGainBig;
    this.effectiveTime = 0;
    setWidth(25);
    setHeight(25);
  }

  @Override
  public void collisionWithPlayer(Player p) {
    if (this.isFinished == false) {

      p.setHealth(p.getHealth() + healthAddition);
      if (p.getHealth() > 100) {
        p.setHealth(100);
      }
      this.isFinished = true; // Indicate this powerup is finished of usingz
    }

  }

}
