package model.GameObjects;

import model.Game;

public abstract class PowerUp extends GameObject {

    protected int appearTime;

    @Override
    public String serialize(){
      // TODO: type for different power ups
      return "PowerUp;";// TODO: type, x,y,dx,dy
    }

    public boolean deserialize(String info){
      //TODO: deserialize it later
      return true;
    }
}
