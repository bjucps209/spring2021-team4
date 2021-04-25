package model.GameObjects.Obstacles;

import model.Level;
import model.Enums.ObstacleTypes;

public class Narrow extends Obstacle{

  public Narrow(Level l) {
    super(l);
    this.type = ObstacleTypes.NARROW;
    setWidth(25);
    setHeight(100);
  }
  
}
