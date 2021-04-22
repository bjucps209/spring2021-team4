package model.GameObjects.Powerups;

import javafx.util.Duration;
import model.Level;
import model.Enums.PowerUps;
import model.GameObjects.Player;

public class TemporaryInvincible extends PowerUp {

  public TemporaryInvincible(Level l) {
    super(l);
    this.type = PowerUps.TemporaryInvincible;
    this.effectiveTime = 5;

  }

  @Override

  public void update() {

    // TODO Auto-generated method stub

    if (passedTime >= effectiveTime) {
      this.isFinished = true;
    }
    if (this.startTime != -1 && passedTime <= effectiveTime) {
      this.passedTime = this.startTime - this.currentLevel.getRemainingTime();

    }

  }

  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    // Add up all duration
    if (this.startTime == -1) {
      this.startTime = this.currentLevel.getRemainingTime();
    }
    if (this.isFinished == false) {
      p.setTemporaryInvincible(true);
    } else {
      p.setTemporaryInvincible(false);
    }

  }

}
