package model.GameObjects.Powerups;

import model.Enums.PowerUps;
import model.GameObjects.GameObject;
import model.GameObjects.Player;

import java.util.ArrayList;
import java.util.Random;

public abstract class PowerUp extends GameObject {

  //Note: powerups will be position by the level editors.
  protected int appearTime;
  protected PowerUps type;
  protected boolean isFinished = false;

  public abstract void collisionWithPlayer(Player p);

  @Override
  public void update() {
    // powerups will not move during the game
  }

  @Override
  public String serialize() {
    // TODO: type for different power ups
    return "PowerUp;";// TODO: type, x,y,dx,dy
  }

  public boolean deserialize(String info) {
    // TODO: deserialize it later
    return true;
  }

  // A factory method for all powerups
  public static PowerUp create(PowerUps type) {
    PowerUp power;
    switch (type) {
    case Freeze: {
      power = new Freeze();
      break;
    }
    case DestroyShip: {
      power = new DestroyShip();
      break;
    }
    case TemporaryInvincible: {
      power = new DestroyShip();
      break;
    }
    case HealthGainBig: {
      power = new HealthGainBig();
    }
    case HealthGainSmall: {
      power = new HealthGainSmall();
    }
    default: {
      // should not happen
      power = new HealthGainSmall();
    }

    }
    return power;

  }

  public int getAppearTime() {
    return appearTime;
  }

  public void setAppearTime(int appearTime) {
    this.appearTime = appearTime;
  }

  public PowerUps getType() {
    return type;
  }

  public void setType(PowerUps type) {
    this.type = type;
  }

  public void setIsFinished(boolean status) {
    this.isFinished = status;
  }

  public boolean getIsFinished() {
    return this.isFinished;
  }
}
