package model.GameObjects.Powerups;

import model.Enums.PowerUps;
import model.GameObjects.Player;

public class Freeze extends PowerUp{

  public Freeze(){
    this.type = PowerUps.Freeze;
  }
  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    
  }
  
}
