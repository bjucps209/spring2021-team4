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
      return new SpeedDownPanel(l);
    }
    else{
      return new SpeedUpPanel(l);
    }
    
  }

 

  public SpeedPanel(Level l) {
    super(l);
    //TODO Auto-generated constructor stub
    this.dx.set(0);
    this.dy.set(0);

    this.height.set(4);
    this.width.set(4);
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
    do{
      possibleX =rand.nextInt( ( (width-6) - 6 ) + 1) + 6;
      possibleY =rand.nextInt( ( (height-6) - 6 ) + 1) + 6;
      o = checkCollision(this.currentLevel.getObstacles()); // make sure not collapse with obstacle
      
    }while(o != null);

    this.x.set(possibleX);
    this.y.set(possibleY);

    //int secretNum = rand.nextInt((max - min) + 1) + min;  //include the max
  }

  public void collisionWithObject(GameObject ob){
   // startTime = this.currentLevel.getRemainingTime();
  }
  
}
