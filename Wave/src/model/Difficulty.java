package model;

import model.Enums.DifficultyLevel;

public class Difficulty {

  protected DifficultyLevel difficulty ;

  public Difficulty(DifficultyLevel choice) {
    this.difficulty = choice;
  }

  public int rewardCoins() {
    switch (difficulty) {
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

  public int difficultyAffect() {

    switch (difficulty) {
    case Easy:
      return 0;
    case Medium:
      return 1;
    case Hard:
      return 3;
    default:
      return -1;
    }
  }

  public String serialization(){
    return this.difficulty.toString();
  }

  public void deserialization(String value){
    this.difficulty = DifficultyLevel.valueOf(value);
  }
  public DifficultyLevel getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(DifficultyLevel difficulty) {
    this.difficulty = difficulty;
  }
}
