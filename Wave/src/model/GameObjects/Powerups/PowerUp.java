//-----------------------------------------------------------
//File:   PowerUp.java
//Desc:   class that all powerups inherit from
//        
//-----------------------------------------------------------

package model.GameObjects.Powerups;

import model.GameObjects.GameObject;
import model.GameObjects.Player;
import model.Level;
import model.Enums.PowerUps;

// abstract class that powerups inherit from
public abstract class PowerUp extends GameObject {

  // Note: powerups will be position by the level editors.

  protected PowerUps type;
  protected boolean isFinished = false;
  protected int effectiveTime;
  protected int passedTime = 0;
  protected int startTime = -1;

  /**
   * set the spawn time of this powerup?
   * 
   * @param p ??
   * @return none
   */
  public void collisionWithPlayer(Player p) {
    startTime = this.currentLevel.getRemainingTime();
  }

  public PowerUp(Level l) {
    super(l);
    this.dx.set(0);
    this.dy.set(0);

    // in the future
    this.appearTime = 60;
  }

  @Override
  public void update() {
    // powerups will not move during the game
  }

  @Override
  public String serialize() {
    return "PowerUp;" + type.toString() + ";" + x.get() + ";" + y.get() + ";" + width.get() + ";" + height.get() + ";"
        + dx.get() + ";" + dy.get() + ";" + appearTime;
  }

  public boolean deserialize(String info) {
    // Note: powerup and type has already been deserialzied at level base
    try {
      String[] infos = info.split(";");
      this.x.set(Integer.parseInt(infos[0]));
      this.y.set(Integer.parseInt(infos[1]));
      this.width.set(Integer.parseInt(infos[2]));
      this.height.set(Integer.parseInt(infos[3]));
      this.dx.set(Integer.parseInt(infos[4]));
      this.dy.set(Integer.parseInt(infos[5]));
      this.appearTime = Integer.parseInt(infos[6]);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * A factory method for all powerups
   * 
   * @param type the type of power up to be created
   * @param l    the level this powerup is being created for
   * @return an instance of the respective power up type
   */
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
      break;
    }
    case HealthGainSmall: {
      power = new HealthGainSmall(l);
      break;
    }
    default: {
      // should not happen
      power = new HealthGainSmall(l);
      break;
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

  public int getPassedTime() {
    return passedTime;
  }

  public void setPassedTime(int passedTime) {
    this.passedTime = passedTime;
  }

  public int getStartTime() {
    return startTime;
  }

  public void setStartTime(int startTime) {
    this.startTime = startTime;
  }

}
