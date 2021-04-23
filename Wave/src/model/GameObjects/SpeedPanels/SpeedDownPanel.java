package model.GameObjects.SpeedPanels;

import model.Level;
import model.Enums.SpeedPanelTypes;
import model.GameObjects.GameObject;

public class SpeedDownPanel extends SpeedPanel{

  public SpeedDownPanel(Level l) {
    super(l);
    //TODO Auto-generated constructor stub
    this.effectiveTime = 5;
    this.type = SpeedPanelTypes.speedDown;
  }

  @Override
  public void collisionWithObject(GameObject ob) {
    if(this.startTime == -1){
      this.startTime = this.currentLevel.getRemainingTime();

    }
    if(isFinished == false){
      ob.setSpeedPanelSpeed(-5);
    }else{
      ob.setSpeedPanelSpeed(0);
    }
  }

  @Override
  public void update() {
    if(this.startTime != -1){
      this.passedTime = this.startTime - this.currentLevel.getRemainingTime();
      if(passedTime >= effectiveTime){
        this.isFinished = true;
      }
    }
  }
  
}
