package model;

import java.io.*;
import org.junit.Test;

import model.Enums.EnemyTypes;
import model.Enums.ShipSkins;

import static org.junit.Assert.*;
import java.util.*;

public class GameTest {

  @Test
  public void testSave_correctInput_expectResult() {

    /**
     * 15 2 easy 1 Jack;SHIP1;100;20;300;450;10;10;Faster,5,20 //Explanation: Faster
     * is an example affect make player move faster. The player gain a increase of
     * speed by5, and for 20 seconds 3 EnemyObject;GHOST;100;230;4;4;Freeze,-5,-5,5
     * Obstacle;Obstacle;100;250;0;0 PowerUp;Freeze;20;50;0;0 ENDL#
     * 
     * 0 3 easy 1 Jack;SHIP1;100;20;300;450;10;10;Faster,5,20 //Explanation: Faster
     * is an example affect make player move faster. The player gain a increase of
     * speed by5, and for 20 seconds ENDL#
     * 
     */
    User currentUser = Wave.getInstance().getCurrentUser();
    Wave.getInstance().getGame().save(currentUser.getName());
    File location = new File(currentUser.getName()+".dat");

    assertTrue(location.exists());  // means saved the file successfully

  }

  @Test
  public void testLoad_correctInput_expectResult() {
    // Example data1
    Wave.getInstance().getGame().load("Jack.dat");
    assertEquals(Wave.getInstance().getGame().getCurrentLevel().getRemainingTime(), 15);   
    assertEquals(Wave.getInstance().getGame().getCurrentLevel(), 2);
    // assertTrue(x, easy); // difficulty level of game has not been implemented yet
    // assertEqual(x,1) //  allowing multiple player part has not been implemented, so now assume 1 player
    var user = Wave.getInstance().getCurrentUser();
    assertTrue(user.getName().equals("Jack"));
    assertTrue(user.getShip() == ShipSkins.SHIP1);
    assertTrue(user.getCoins() == 100);

    var player = Wave.getInstance().getGame().getPlayer();  // need to change as more than 1 player possible
    assertTrue(Wave.getInstance().getGame().getCurrentLevel().getScore() == 20);
    assertTrue(player.getX() ==300);
    assertTrue(player.getY() == 450);
    assertTrue(player.getDx() == 10);
    assertTrue(player.getDy() == 10);
    // has not implement the powerup and addition affect yet, so skip testing in this stage
    var game = Wave.getInstance().getGame();
    assertTrue(game.getCurrentLevel().getAllObjects().size() == 3);

    var enemy1 = game.getCurrentLevel().getEnemies().get(0);
    assertTrue(enemy1.getType() == EnemyTypes.GHOST);
    assertTrue(enemy1.getX() == 100);
    assertTrue(enemy1.getY() == 230);
    assertTrue(enemy1.getDx() == 4);
    assertTrue(enemy1.getDy() == 4);
    // Since the power up has not been implement, so skip first

    var obstacle1 = game.getCurrentLevel().getObstacles().get(0);
    assertTrue(obstacle1.getX() == 100);
    assertTrue(obstacle1.getY() == 250);
    assertTrue(obstacle1.getDx() == 0);
    assertTrue(obstacle1.getDy() == 0);

    // since the powerup has not been implement, so skip first



    // Example 2 data
    Wave.getInstance().getGame().load("Andrew.dat");
    game = Wave.getInstance().getGame();

    assertTrue(game.getCurrentLevel().getRemainingTime() == 60);
    assertTrue(game.getLevelNum() == 4);
    // has not implement the difficulty functionalty
    // has not implement multiple player feature yet
    
    // hard coding, need to change later, assuming for only 1 user
    user = Wave.getInstance().getCurrentUser();
    player = game.getPlayer();

    assertTrue(user.getName().equals("Andrew"));
    assertTrue(user.getShip() == ShipSkins.SHIP1);
    assertTrue(user.getCoins() == 100);
    assertTrue(game.getCurrentLevel().getScore() == 20);
    assertTrue(player.getX() == 300);
    assertTrue(player.getY() == 450);
    assertTrue(player.getDx() == 10);
    assertTrue(player.getDy() == 10);
    // since has not implement additonal features here, so skip for testing


  }
}