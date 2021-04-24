package model.GameObjects.SpeedPanels;
import java.util.Random;
import model.Level;
import model.Wave;
import model.Enums.SpeedPanelTypes;
import model.GameObjects.GameObject;

public class SpeedPanel extends GameObject{

  SpeedPanelTypes type;
  protected boolean isFinished = false;
  protected int effectiveTime = 5;
  protected int passedTime = 0;
  protected int startTime = -1;
  
  
  public static SpeedPanel create(SpeedPanelTypes type, Level l){
    if(type == SpeedPanelTypes.speedDown){
      SpeedDownPanel s = new SpeedDownPanel(l);
      s.generatePosition();
      return s;
      //return new SpeedDownPanel(l);
    }
    else{
      SpeedUpPanel s = new SpeedUpPanel(l);
      s.generatePosition();
      return s;
    }
    
  }

 

  public SpeedPanel(Level l) {
    super(l);
    //TODO Auto-generated constructor stub
    this.dx.set(0);
    this.dy.set(0);

    this.height.set(50);
    this.width.set(50);
    }   

  public SpeedPanelTypes getType() {
    return type;
  }



  public void setType(SpeedPanelTypes type) {
    this.type = type;
  }



  public boolean isFinished() {
    return isFinished;
  }



  public void setFinished(boolean isFinished) {
    this.isFinished = isFinished;
  }



  public int getEffectiveTime() {
    return effectiveTime;
  }



  public void setEffectiveTime(int effectiveTime) {
    this.effectiveTime = effectiveTime;
  }



  public int getPassedTime() {
    return passedTime;
  }



  public void setPassedTime(int passedTime) {
    this.passedTime = passedTime;
  }



  public int getStartTime() {
    return startTime;
  }



  public void setStartTime(int startTime) {
    this.startTime = startTime;
  }



  @Override
  public void update(){
    // speed pane will not move during the game
  }
  @Override
  public String serialize() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean deserialize(String info) {
    // TODO Auto-generated method stub
    return false;
  }

  public void generatePosition(){
    Random rand = new Random();
    int width = Wave.getInstance().getGame().getGameWidth();
    int height = Wave.getInstance().getGame().getGameHeight();

    int possibleX;
    int possibleY;
    GameObject o;
    GameObject s;
    do{
      possibleX =rand.nextInt( ( (width-6) - 6 ) + 1) + 6;
      possibleY =rand.nextInt( ( (height-6) - 6 ) + 1) + 6;
      o = checkCollision(this.currentLevel.getObstacles()); // make sure not collapse with obstacle
      s = checkCollision(this.currentLevel.getSpeedPanels());
    }while(o != null);

    this.x.set(possibleX);
    this.y.set(possibleY);

    //int secretNum = rand.nextInt((max - min) + 1) + min;  //include the max
  }

  public void collisionWithObject(GameObject ob){
   // startTime = this.currentLevel.getRemainingTime();
  }
  
}
