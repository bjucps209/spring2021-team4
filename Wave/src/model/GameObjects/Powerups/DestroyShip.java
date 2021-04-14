package model.GameObjects.Powerups;

import java.util.ArrayList;

import model.Level;
import model.Wave;
import model.Enums.PowerUps;
import model.GameObjects.Player;
import model.GameObjects.Enemies.EnemyObject;
import java.util.Random;
public class DestroyShip extends PowerUp{


  DestroyShip(Level l){
    super(l);
    this.type = PowerUps.DestroyShip;
  }
  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    Random ran = new Random();
    ArrayList<EnemyObject>enemies = Wave.getInstance().getGame().getCurrentLevel().getEnemies();
    
    int enemyIndex = ran.nextInt( ((enemies.size()-1)-0) + 1) + 0;

    //TODO method of deleting variable

  }
  
}
