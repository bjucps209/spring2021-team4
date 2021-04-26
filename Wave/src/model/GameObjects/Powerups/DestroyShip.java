//-----------------------------------------------------------
//File:   DestroyShip.java
//Desc:   powerup that destroys a random ship in the current level
//        
//-----------------------------------------------------------

package model.GameObjects.Powerups;

import java.util.ArrayList;

import model.Level;
import model.Wave;
import model.Enums.PowerUps;
import model.GameObjects.Player;
import model.GameObjects.Enemies.EnemyObject;
import java.util.Random;

public class DestroyShip extends PowerUp {

  public DestroyShip(Level l) {
    super(l);
    this.type = PowerUps.DestroyShip;
    this.effectiveTime = 0;
    setWidth(25);
    setHeight(25);
  }

  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    if (this.isFinished == false) {
      Random ran = new Random();
      ArrayList<EnemyObject> enemies = Wave.getInstance().getGame().getCurrentLevel().getEnemies();

      int enemyIndex = ran.nextInt(((enemies.size() - 1) - 0) + 1) + 0;
      // TODO: delete

      // TODO method of deleting variable
      currentLevel.getAllObjects().remove(currentLevel.getEnemies().get(enemyIndex));
      currentLevel.getEnemies().get(enemyIndex).setIsRemoved(true);
      currentLevel.getEnemies().remove(enemyIndex);
      this.isFinished = true;
    }

  }

}
