package model.GameObjects.Powerups;

// import model.Wave;
import model.Enums.PowerUps;
import model.GameObjects.Player;

public class DestroyShip extends PowerUp{


  DestroyShip(){
    this.type = PowerUps.DestroyShip;
  }
  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub

  }
  
}
