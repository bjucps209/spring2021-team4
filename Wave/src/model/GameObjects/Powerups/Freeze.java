//-----------------------------------------------------------
//File:   Freeze.java
//Desc:   class that freezes all entities in a level for 5 
//        seconds
//-----------------------------------------------------------

package model.GameObjects.Powerups;

import model.Level;
import model.Enums.PowerUps;
import model.GameObjects.Player;
import model.GameObjects.Enemies.EnemyObject;

// freezes all entities in the game for 5 seconds when hit
public class Freeze extends PowerUp {

  public Freeze(Level l) {
    super(l);
    this.type = PowerUps.Freeze;
    this.effectiveTime = 5;
    this.isFinished = false;
    setWidth(25);
    setHeight(25);
  }

  @Override
  public void collisionWithPlayer(Player p) {
    if (this.startTime == -1) {
      this.startTime = this.currentLevel.getRemainingTime();
    }
    if (isFinished == false) {
      for (EnemyObject ob : this.currentLevel.getEnemies()) {
        ob.setTemporaryFreeze(true);
      }
    } else {
      for (EnemyObject ob : this.currentLevel.getEnemies()) {
        ob.setTemporaryFreeze(false);
      }
    }

  }

  @Override

  public void update() {
    if (this.startTime != -1) {
      this.passedTime = this.startTime - this.currentLevel.getRemainingTime();
      if (passedTime >= effectiveTime) {
        this.isFinished = true;
      }
    }

  }

}
