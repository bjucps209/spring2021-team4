//-----------------------------------------------------------
//File:   Large.java
//Desc:   large obstacle
//        
//-----------------------------------------------------------

package model.GameObjects.Obstacles;

import model.Level;
import model.Enums.ObstacleTypes;

// large rectangular obstacle. has no method of movement
public class Large extends Obstacle {

  public Large(Level l) {
    super(l);
    this.type = ObstacleTypes.LARGE;
    setWidth(50);
    setHeight(100);
  }
}
