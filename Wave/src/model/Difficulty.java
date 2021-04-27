//-----------------------------------------------------------
//File:   Difficulty.java
//Desc:   file used to calculate attributes of the game affected
//        by difficulty selection
//-----------------------------------------------------------s

package model;

import model.Enums.DifficultyLevel;

// class that computes certain aspects of the game instance based on the selected difficulty level
public class Difficulty {

  protected DifficultyLevel difficulty ;

  public Difficulty(DifficultyLevel choice) {
    this.difficulty = choice;
  }

  /**
   * method to reward a player with a number of coins based on the difficulty level
   * @param none
   * @return the number of coins to be rewarded
   */
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

  /**
   * method to calculate a damage multiplier based on the difficulty of the game
   * @param none
   * @return a multiplier representing the extra damage to be done to the player
   */
  public int difficultyAffect() {

    switch (difficulty) {
    case Easy:
      return 0;
    case Medium:
      return 3;
    case Hard:
      return 5;
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
