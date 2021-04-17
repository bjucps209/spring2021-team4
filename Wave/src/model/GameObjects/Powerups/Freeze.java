package model.GameObjects.Powerups;

import model.Level;
import model.Enums.PowerUps;
import model.GameObjects.Player;

public class Freeze extends PowerUp{



  public Freeze(Level l){
    super(l);
    this.type = PowerUps.Freeze;
    this.effectiveTime = 5;
  
  }
  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    
  }

  
}
