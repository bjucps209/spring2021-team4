package model.GameObjects.Powerups;

import model.Enums.PowerUps;
import model.GameObjects.Player;

public class HealthGainSmall extends PowerUp{
    //Note: this implement the small gain package in the game specification
    private final static int healthAddition = 10;

    public HealthGainSmall(){
      this.type = PowerUps.HealthGainSmall;
    }
    @Override
    public void collisionWithPlayer(Player p) {
      // TODO Auto-generated method stub
      p.setHealth(p.getHealth() + healthAddition);
      this.isFinished = true;
      
    }


}
