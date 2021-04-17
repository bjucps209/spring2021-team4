package model.GameObjects.Obstacles;

import model.Level;
import model.Enums.ObstacleTypes;

public class Square extends Obstacle{

  public Square() {
    super();
    this.type = ObstacleTypes.SQUARE;
  }
  
}
