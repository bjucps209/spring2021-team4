package model.GameObjects.Powerups;

import model.Level;
import model.Enums.PowerUps;
import model.GameObjects.Player;
import model.GameObjects.Enemies.EnemyObject;

public class Freeze extends PowerUp {

  public Freeze(Level l) {
    super(l);
    this.type = PowerUps.Freeze;
    this.effectiveTime = 5;

  }

  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
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
    // TODO Auto-generated method stub

    if (this.startTime != -1) {
      this.passedTime = this.startTime - this.currentLevel.getRemainingTime();
      if (passedTime >= effectiveTime) {
        this.isFinished = true;
      }
    }

  }

}
