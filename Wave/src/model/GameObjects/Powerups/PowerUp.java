package model.GameObjects.Powerups;

import model.GameObjects.GameObject;
import model.GameObjects.Player;
import model.Level;
import model.Enums.PowerUps;

import java.util.ArrayList;
import java.util.Random;

public abstract class PowerUp extends GameObject {

  // Note: powerups will be position by the level editors.
  protected int appearTime;
  protected PowerUps type;
  protected boolean isFinished = false;
  protected int effectiveTime;
  public abstract void collisionWithPlayer(Player p);

  public PowerUp(Level l) {
    super(l);
  }

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
  public static PowerUp create(PowerUps type, Level l) {
    PowerUp power;
    switch (type) {
    case Freeze: {
      power = new Freeze(l);
      break;
    }
    case DestroyShip: {
      power = new DestroyShip(l);
      break;
    }
    case TemporaryInvincible: {
      power = new TemporaryInvincible(l);
      break;
    }
    case HealthGainBig: {
      power = new HealthGainBig(l);
    }
    case HealthGainSmall: {
      power = new HealthGainSmall(l);
    }
    default: {
      // should not happen
      power = new HealthGainSmall(l);
    }

    }
    return power;

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

  public int getEffectiveTime() {
    return effectiveTime;
  }

  public void setEffectiveTime(int effectiveTime) {
    this.effectiveTime = effectiveTime;
  }

  public void setFinished(boolean isFinished) {
    this.isFinished = isFinished;
  }

  public int getAppearTime() {
    return this.appearTime;
  }

  public void setAppearTime(int appearTime) {
    this.appearTime = appearTime;
  }
}
