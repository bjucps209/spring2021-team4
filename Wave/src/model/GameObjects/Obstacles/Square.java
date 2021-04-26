//-----------------------------------------------------------
//File:   Square.java
//Desc:   square obstacle
//        
//-----------------------------------------------------------

package model.GameObjects.Obstacles;

import model.Level;
import model.Enums.ObstacleTypes;

public class Square extends Obstacle{

  public Square(Level l) {
    super(l);
    this.type = ObstacleTypes.SQUARE;
  }
  
}
