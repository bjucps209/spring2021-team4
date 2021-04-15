package model.GameObjects.Powerups;

import javafx.util.Duration;
import model.Enums.PowerUps;
import model.GameObjects.Player;

public class Freeze extends PowerUp{



  public Freeze(){
    this.type = PowerUps.Freeze;
    this.effectiveTime = 5;
  }
  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    
  }
  
}
