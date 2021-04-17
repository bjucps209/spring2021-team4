package model.GameObjects.Obstacles;

import model.Level;
import model.Enums.ObstacleTypes;

public class Corner extends Obstacle{

  public Corner(Level l) {
    super(l);
    this.type = ObstacleTypes.CORNER;
  }

  
}
