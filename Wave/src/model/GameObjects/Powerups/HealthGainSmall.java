//-----------------------------------------------------------
//File:   HealthGainSmall.java
//Desc:   powerup that gives the player 10 health
//        
//-----------------------------------------------------------

package model.GameObjects.Powerups;

import model.Level;
import model.Enums.PowerUps;
import model.GameObjects.Player;

// class that gives the player 10 health and contains methods how to interact with the player
public class HealthGainSmall extends PowerUp {
  // Note: this implement the small gain package in the game specification
  private final static int healthAddition = 10;

  public HealthGainSmall(Level l) {
    super(l);
    this.type = PowerUps.HealthGainSmall;
    this.effectiveTime = 0;
    setWidth(25);
    setHeight(25);  
  }

  @Override
  public void collisionWithPlayer(Player p) {
    if (this.isFinished == false) {
      p.setHealth(p.getHealth() + healthAddition);
      if(p.getHealth()> 100){
        p.setHealth(100);
      }
      this.isFinished = true;
    }

  }

}
