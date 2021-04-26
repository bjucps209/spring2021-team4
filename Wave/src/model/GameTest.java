package model;

import java.io.*;
import java.util.ArrayList;

import org.junit.Test;

import model.Enums.DifficultyLevel;
import model.Enums.EnemyTypes;
import model.Enums.ShipSkins;
import model.Enums.ObstacleTypes;
import model.Enums.PowerUps;
import model.GameObjects.Obstacles.*;
import model.GameObjects.Powerups.Freeze;
import model.GameObjects.Powerups.PowerUp;
import model.GameObjects.Powerups.TemporaryInvincible;
import model.GameObjects.*  ;
import model.GameObjects.Player;
import model.GameObjects.Enemies.Bouncer;
import model.GameObjects.Enemies.EnemyObject;
import static org.junit.Assert.*;

public class GameTest {


  @Test
  public void testLoad_example1_expectResult() throws IOException{
    // Example 1
    Wave.getInstance().loadAllUsers();
    Wave.getInstance().setCurrentUser(new User("AndrewTest"));
    Game game = new Game();
    for(int i = 0; i < 9; i++){  // already has one level when Wave.getInstance().gameStart()
      game.getLevels().add(new Level());
    }


    


    assertTrue(game.load("Example1Serialization")); 
    User user = Wave.getInstance().getCurrentUser();
    Player player = game.getCurrentLevel().getPlayer();
    assertTrue(game.getLevelNum() == 2);
    assertTrue(Wave.getInstance().getCoins() == 20);
    assertTrue(game.getDifficultyLevel().getDifficulty() == DifficultyLevel.Easy);

    assertTrue(game.getCurrentLevel().getRemainingTime() == 15);
    
    assertTrue(user.getName().equals("Jack"));
    assertTrue(user.getCoins() == 100);
    assertTrue(game.getCurrentLevel().getScore() == 20);
    assertTrue(player.getHealth() == 58);
    assertTrue(player.getCurrentShipSkins() == ShipSkins.SHIP1);
    assertTrue(player.getX() == 300);
    assertTrue(player.getY() == 450);
    assertTrue(player.getWidth() == 5);
    assertTrue(player.getHeight() == 5);
    assertTrue(player.getDx() == 10 );
    assertTrue(player.getDy() == 10);
    
    assertTrue(player.getHits().size() == 1);
    assertTrue(player.getHits().get(0) instanceof TemporaryInvincible);
    TemporaryInvincible ob = (TemporaryInvincible)player.getHits().get(0);
    assertTrue(ob.getEffectiveTime() == 5);
    assertTrue(ob.getPassedTime() == 3);
    // note delete later
    //TODO: later assertTrue(ob.getAppearTime() ==60);
    assertTrue(ob.getStartTime() == 18);

    Level currentLevel = game.getCurrentLevel();
    //assertTrue(game.getCurrentLevel().getAllObjects().size()-1 == 3); TODO: have not implement powerUP yet, so it will be wrong 

    assertTrue(currentLevel.getEnemies().size() == 1);
    EnemyObject enemy = currentLevel.getEnemies().get(0);
    assertTrue(enemy.getType() == EnemyTypes.BOUNCER);
    assertTrue(enemy.getX() == 100);
    assertTrue(enemy.getY() == 230);
    assertTrue(enemy.getWidth() == 5);
    assertTrue(enemy.getHeight() == 5);
    assertTrue(enemy.getDx() == 4);
    assertTrue(enemy.getDy() == 4);
    assertTrue(enemy.getAppearTime() == 60);
    //TODO: special affect on enemy needs to be checked


    assertTrue(currentLevel.getObstacles().size() == 1);
    Obstacle obstalce = currentLevel.getObstacles().get(0);
    // TODO: Obstacle have different type?
    assertTrue(obstalce.getType() == ObstacleTypes.SQUARE);
    assertTrue(obstalce.getX() == 100);
    assertTrue(obstalce.getY() == 250);
    assertTrue(obstalce.getWidth() == 5);
    assertTrue(obstalce.getHeight() == 5);
    assertTrue(obstalce.getDx() == 0 && obstalce.getDy() == 0);
    assertTrue(obstalce.getAppearTime() == 40);


    assertTrue(currentLevel.getPowerUps().size() == 1);
    PowerUp power = currentLevel.getPowerUps().get(0);
    assertTrue(power.getType() == PowerUps.Freeze);
    assertTrue(power.getX() == 20);
    assertTrue(power.getY() == 50);
    assertTrue(power.getWidth() == 5);
    assertTrue(power.getHeight() == 5);
    assertTrue(power.getDx() == 0 && power.getDy() == 0);
    assertTrue(power.getAppearTime() == 30);



    assertTrue(currentLevel.getAllObjects().size()-1 == 4); // player is also in the list

  }

  @Test
  public void testLoad_example2_expectResult() throws IOException {
    // Example 2
    
    /*ArrayList<Level> levels = new ArrayList<>();
    levels.add(Wave.loadCustomLevel("level0"));
    levels.add(Wave.loadCustomLevel("level1"));
    levels.add(Wave.loadCustomLevel("level2"));
    levels.add(Wave.loadCustomLevel("level3"));
    levels.add(Wave.loadCustomLevel("level4"));
    Wave.getInstance().gameStart(new ArrayList<Level>());
    Game game = Wave.getInstance().getGame();*/
    Wave.getInstance().loadAllUsers();
    Wave.getInstance().setCurrentUser(new User("AndrewTest"));
    Game game = new Game();
    for(int i = 0; i < 9; i++){  // already has one level when Wave.getInstance().gameStart()
      game.getLevels().add(new Level());
    }

    assertTrue(game.load("Example2Serialization")); 

    User user = Wave.getInstance().getCurrentUser();
    Player player = game.getCurrentLevel().getPlayer();
    Level currentLevel = game.getCurrentLevel();

    assertTrue(game.getLevelNum() == 4);
    assertTrue(currentLevel.getRemainingTime() == 60);

    
    assertTrue(Wave.getInstance().getCoins() == 40);
    assertTrue(game.getDifficultyLevel().getDifficulty() == DifficultyLevel.Easy);
    assertTrue(user.getName().equals("AndrewTest"));
    assertTrue(user.getCoins() == 200);
    assertTrue(currentLevel.getScore() == 25);
    
    assertTrue(player.getHealth() == 30);
    assertTrue(player.getCurrentShipSkins() == ShipSkins.SHIP1);
    assertTrue(user.getShip() == ShipSkins.SHIP1);
    assertTrue(player.getX() == 300);
    assertTrue(player.getY() == 450);
    assertTrue(player.getWidth() == 5);
    assertTrue(player.getHeight() == 5);
    assertTrue(player.getDx() == 10);
    assertTrue(player.getDy() == 10);

    assertTrue(currentLevel.getAllObjects().size()-1 == 0);  //Note: -1 ince player() is also in the AllObject lists
  }

  @Test
  public void testLoad_example3_correctResult(){
    // TODO: in beta version
    assertTrue(true);
  }

  @Test
  public void testLoad_example4_correctResult(){
    Wave.getInstance().loadAllUsers();
    Wave.getInstance().setCurrentUser(new User("AndrewTest"));
    Game game = new Game();
    for(int i = 0; i < 9; i++){  // already has one level when Wave.getInstance().gameStart()
      game.getLevels().add(new Level());
    }
    assertTrue(game.load("Example4Serialization")); 

    Level currentLevel = game.getCurrentLevel();

    assertTrue(game.getLevelNum() == 0); // first level of game
    //assertTrue(currentLevel.getRemainingTime() == 60); // no more remaing time
  }

  @Test
  public void testSave_middlleOfGameSingelPlayer_correctResult(){
    //TODO: need implement same thing for multiple player
    Wave.getInstance().loadAllUsers();
    Wave.getInstance().setCurrentUser(new User("AndrewTest"));
    Game game = new Game();
    for(int i = 0; i < 9; i++){  // already has one level when Wave.getInstance().gameStart()
      game.getLevels().add(new Level());
    }

    assertTrue(game.load("Example2Serialization")); 
    Level currentLevel = game.getCurrentLevel();
    User currentUser = Wave.getInstance().getCurrentUser();
    Player player = currentLevel.getPlayer();

    currentLevel.setRemainingTime(20);
    currentUser.setCoins(130);
    currentLevel.setScore(30);
    player.setHealth(20);
    player.setX(350);
    player.setY(380);
    player.setDx(200);
    player.setDy(200);
    
    TemporaryInvincible s = new TemporaryInvincible(currentLevel);
    s.setPassedTime(1);
    s.setEffectiveTime(5);
    s.setAppearTime(20);
    player.getHits().add(s);

    Bouncer bouncer = new Bouncer(currentLevel);
    bouncer.setType(EnemyTypes.BOUNCER);
    bouncer.setX(20);
    bouncer.setY(20);
    bouncer.setDx(4);
    bouncer.setDy(4);
    bouncer.setWidth(5);
    bouncer.setHeight(5);
    // TODO: set special affects later in

    Obstacle ob = Obstacle.create(ObstacleTypes.SQUARE, currentLevel);
    ob.setType(ObstacleTypes.LARGE);
    ob.setX(20);
    ob.setY(330);
    ob.setWidth(5);
    ob.setHeight(5);

    PowerUp pow = new Freeze(currentLevel);
    pow.setType(PowerUps.Freeze);
    pow.setX(100);
    pow.setY(200);
    pow.setDx(0);
    pow.setDx(0);
    pow.setWidth(10);
    pow.setHeight(10);
    pow.setAppearTime(20);

    currentLevel.getAllObjects().add(bouncer);
    currentLevel.getAllObjects().add(ob);
    currentLevel.getAllObjects().add(pow);

    currentLevel.getEnemies().add(bouncer);
    currentLevel.getObstacles().add(ob);
    currentLevel.getPowerUps().add(pow);

    // set points
    Wave.getInstance().setCoins(45);

    game.save("SinglePlayerofMiddleGame");

    try(BufferedReader rd = new BufferedReader(new FileReader("SinglePlayerofMiddleGame.txt"))){

      assertTrue(rd.readLine().equals("20"));
      assertTrue(rd.readLine().equals("4"));
      assertTrue(rd.readLine().equals("45"));  
      assertTrue(rd.readLine().equals("Easy"));
      assertTrue(rd.readLine().equals("1"));
      assertTrue(rd.readLine().equals("###user"));
      assertTrue(rd.readLine().equals("AndrewTest;130;30;20;SHIP1;350;380;5;5;200;200;PowerUp,TemporaryInvincible,5,1,-1"));
      assertTrue(rd.readLine().equals("3")); // should be 3 if add in powerup
      assertTrue(rd.readLine().equals("###gameobject"));
      assertTrue(rd.readLine().equals("EnemyObject;BOUNCER;20;20;5;5;4;4;60"));  //TODO: test for speical affects later on
      assertTrue(rd.readLine().equals("###gameobject"));
      assertTrue(rd.readLine().equals("Obstacle;LARGE;20;330;5;5;0;0;60"));
      assertTrue(rd.readLine().equals("###gameobject"));
      assertTrue(rd.readLine().equals("PowerUp;Freeze;100;200;10;10;0;0;20"));

      assertTrue(rd.readLine().equals("ENDL#"));

    }catch (Exception e){

    }
    
  

  }


  @Test
  public void testSave_endOfGameSinglePlayer_correctResult(){
        //TODO: need implement same thing for multiple player
        Wave.getInstance().loadAllUsers();
        Wave.getInstance().setCurrentUser(new User("AndrewTest"));
        Game game = new Game();
        for(int i = 0; i < 9; i++){  // already has one level when Wave.getInstance().gameStart()
          game.getLevels().add(new Level());
        }
    
        assertTrue(game.load("endOfGame")); 
        Level currentLevel = game.getCurrentLevel();
        User currentUser = Wave.getInstance().getCurrentUser();
        Player player = currentLevel.getPlayer();

        currentLevel.setRemainingTime(0);

        game.save("SinglePlayerEndGame");
        try(BufferedReader rd = new BufferedReader(new FileReader("SinglePlayerEndGame.txt"))){
          assertTrue(rd.readLine().equals("60"));
          assertTrue(rd.readLine().equals("4"));
          assertTrue(rd.readLine().equals("0"));
          assertTrue(rd.readLine().equals("Easy"));
          assertTrue(rd.readLine().equals("1"));
          assertTrue(rd.readLine().equals("###user"));
          assertTrue(rd.readLine().equals("AndrewTest;200;25;30;SHIP1;300;450;5;5;10;10"));
          assertTrue(rd.readLine().equals("0"));
          assertTrue(rd.readLine().equals("ENDL#"));
        }catch( IOException e){

        }
    
  }
}