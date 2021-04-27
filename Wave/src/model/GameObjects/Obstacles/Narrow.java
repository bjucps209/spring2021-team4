//-----------------------------------------------------------
//File:   Narrow.java
//Desc:   narrow obstacle
//        
//-----------------------------------------------------------

package model.GameObjects.Obstacles;

import model.Level;
import model.Enums.ObstacleTypes;

// long and narrow obstacle. has no method of movement
public class Narrow extends Obstacle{

  public Narrow(Level l) {
    super(l);
    this.type = ObstacleTypes.NARROW;
    setWidth(25);
    setHeight(100);
  }
  
}
