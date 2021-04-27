package model.GameObjects.SpeedPanels;


import model.Level;
import model.Enums.SpeedPanelTypes;
import model.GameObjects.GameObject;

public class SpeedUpPanel extends SpeedPanel {

  public SpeedUpPanel(Level l) {
    super(l);
    this.type = SpeedPanelTypes.speedUp;
    this.effectiveTime = 5;
    setWidth(50);
    setHeight(50);
  }
  


  @Override
  public void collisionWithObject(GameObject ob) {
    if(this.startTime == -1){
      this.startTime = this.currentLevel.getRemainingTime();
    }
    if(isFinished == false){
      ob.setSpeedPanelSpeed(50);
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
