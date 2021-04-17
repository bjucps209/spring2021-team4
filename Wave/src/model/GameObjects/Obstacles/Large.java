package model.GameObjects.Obstacles;

import model.Level;
import model.Enums.ObstacleTypes;

public class Large extends Obstacle{

  public Large( Level l) {
    super(l);
    this.type = ObstacleTypes.LARGE;
    
  }
  
}
