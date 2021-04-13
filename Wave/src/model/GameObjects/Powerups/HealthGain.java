package model.GameObjects.Powerups;

import model.GameObjects.Player;

public class HealthGain extends PowerUp{
    //Note: this implement the small gain package in the game specification
    int healthAddition = 10;

    @Override
    public void collisionWithPlayer(Player p) {
      // TODO Auto-generated method stub
      p.setHealth(p.getHealth() + healthAddition);
      
    }


}
