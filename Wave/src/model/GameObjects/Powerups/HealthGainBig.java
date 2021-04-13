package model.GameObjects.Powerups;

public class HealthGainBig extends PowerUp {
  private int healthAddition = 50;

  public HealthGainBig(){
    this.type = PowerUps.HealthGainBig;
  }
  @Override
  public void collisionWithPlayer(Player p) {
    // TODO Auto-generated method stub
    p.setHealth(p.getHealth() + healthAddition);
    
  }

}
