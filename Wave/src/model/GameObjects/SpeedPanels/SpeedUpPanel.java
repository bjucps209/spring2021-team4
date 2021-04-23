package model.GameObjects.SpeedPanels;


import model.Level;
import model.Enums.SpeedPanelTypes;
import model.GameObjects.GameObject;

public class SpeedUpPanel extends SpeedPanel {

  public SpeedUpPanel(Level l) {
    super(l);
    //TODO Auto-generated constructor stubt
    this.type = SpeedPanelTypes.speedUp;
    this.effectiveTime = 5;
  }
  


  @Override
  public void collisionWithObject(GameObject ob) {
    // TODO Auto-generated method stub
    if(this.startTime == -1){
      this.startTime = this.currentLevel.getRemainingTime();
    }
    if(isFinished == false){
      ob.setSpeedPanelSpeed(3);
    }else{
      ob.setSpeedPanelSpeed(0);
    }
  }

  @Override
  public void update() {
    // TODO Auto-generated method stub
    if(this.startTime != -1){
      this.passedTime = this.startTime - this.currentLevel.getRemainingTime();
      if(passedTime >= effectiveTime){
        this.isFinished = true;
      }
    }
  }
  
}
