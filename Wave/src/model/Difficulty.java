package model;
import model.Enums.DifficultyLevel;

public class Difficulty {
  
  private DifficultyLevel difficulty;

  public Difficulty(DifficultyLevel choice){
    this.difficulty = choice;
  }
  

  public int rewardCoins(){
    switch(difficulty){
      case Easy:
        return 100;
      case Medium:
        return 200;
      case Hard:
        return 300;
      default:
        return 0;
    }
  }
  public int difficultyAffect(){
    int returnValue;
    switch(difficulty){
      case Easy:
        returnValue = 0;
        break;
      case Medium:
        returnValue = 3;
        break;
      case Hard:
        returnValue = 5;
        break;
      default:
        returnValue = -1;
      
      
    }
    return returnValue;
  }
}
