//-----------------------------------------------------------
//File:   WaveTest.java
//Desc:   unit tests for Wave
//        
//-----------------------------------------------------------

package model;

import java.io.*;
import org.junit.Test;

import model.Enums.ShipSkins;
import static org.junit.Assert.*;

// class that houses unit tests for the Wave class
public class WaveTest {

  /*@Test
  public void testGame_objectMovement() {
    Wave.getInstance().gameStart();
    assertTrue(Wave.getInstance().getGame().getCurrentLevel().getEnemies().get(0).getX() == 40);
    Wave.getInstance().getGame().update();
    assertTrue(Wave.getInstance().getGame().getCurrentLevel().getEnemies().get(0).getX() == 45);
    Wave.getInstance().getGame().getCurrentLevel().getEnemies().get(0).setDx(-5);
    Wave.getInstance().getGame().update();
    assertTrue(Wave.getInstance().getGame().getCurrentLevel().getEnemies().get(0).getX() == 40);
  }*/

  @Test
  public void testLoadAllUsers_sampleExample_correctResult(){
    System.out.println(new File("sampleUserInfo.txt").exists());
    assertTrue(Wave.getInstance().loadAllUsersTest("sampleUserInfo.txt"));
    assertTrue(Wave.getInstance().getUsers().size() == 2);

    // First user in sample
    assertTrue(Wave.getInstance().getUsers().get(0).getName().equals("Jack"));
    assertTrue(Wave.getInstance().getUsers().get(0).getCoins() == 10);
    assertTrue(Wave.getInstance().getUsers().get(0).getShip() == ShipSkins.SHIP2);
    assertTrue(Wave.getInstance().getUsers().get(0).getOwnedShipSkins().size() == 2);
    assertTrue(Wave.getInstance().getUsers().get(0).getOwnedShipSkins().get(0) ==  ShipSkins.SHIP1);
    assertTrue(Wave.getInstance().getUsers().get(0).getOwnedShipSkins().get(1) ==  ShipSkins.SHIP2);

    // Second user in sample
    assertTrue(Wave.getInstance().getUsers().get(1).getName().equals("David"));
    assertTrue(Wave.getInstance().getUsers().get(1).getCoins() == 200);
    assertTrue(Wave.getInstance().getUsers().get(1).getShip() == ShipSkins.SHIP1);
    assertTrue(Wave.getInstance().getUsers().get(1).getOwnedShipSkins().size() == 1);
    assertTrue(Wave.getInstance().getUsers().get(1).getOwnedShipSkins().get(0) == ShipSkins.SHIP1);


  }

  @Test
  public void testSaveAllUser_sampleExample_correctResult(){
    
    Wave.getInstance().loadAllUsersTest("sampleUserInfo.txt");
    
    // changing some information here
    User user2 = Wave.getInstance().getUsers().get(1);
    user2.setCoins(2);
    user2.getOwnedShipSkins().add(ShipSkins.SHIP3);
    user2.setShip(ShipSkins.SHIP3);

    User newUSer = new User("HEllo");
    newUSer.setCoins(20);
    newUSer.setShip(ShipSkins.SHIP1);
    //newUSer.getOwnedShipSkins().add(ShipSkins.SHIP1);
    Wave.getInstance().setCurrentUser(newUSer);

    Wave.getInstance().getUsers().add(newUSer);

    assertTrue(Wave.getInstance().saveAllUsersTest("sampleSaveUserInfo.txt"));

    // now load back again and see

    assertTrue(Wave.getInstance().loadAllUsersTest("sampleSaveUserInfo.txt"));

    assertTrue(Wave.getInstance().getUsers().size() == 3);

    // First user in sample
    assertTrue(Wave.getInstance().getUsers().get(0).getName().equals("Jack"));
    assertTrue(Wave.getInstance().getUsers().get(0).getCoins() == 10);
    assertTrue(Wave.getInstance().getUsers().get(0).getShip() == ShipSkins.SHIP2);
    assertTrue(Wave.getInstance().getUsers().get(0).getOwnedShipSkins().size() == 2);
    assertTrue(Wave.getInstance().getUsers().get(0).getOwnedShipSkins().get(0) ==  ShipSkins.SHIP1);
    assertTrue(Wave.getInstance().getUsers().get(0).getOwnedShipSkins().get(1) ==  ShipSkins.SHIP2);

    
    // Second user in sample
    assertTrue(Wave.getInstance().getUsers().get(1).getName().equals("David"));
    assertTrue(Wave.getInstance().getUsers().get(1).getCoins() == 2);
    assertTrue(Wave.getInstance().getUsers().get(1).getShip() == ShipSkins.SHIP3);
    assertTrue(Wave.getInstance().getUsers().get(1).getOwnedShipSkins().size() == 2);
    assertTrue(Wave.getInstance().getUsers().get(1).getOwnedShipSkins().get(0) == ShipSkins.SHIP1);
    assertTrue(Wave.getInstance().getUsers().get(1).getOwnedShipSkins().get(1) == ShipSkins.SHIP3);

    
    // Third user in sample
    assertTrue(Wave.getInstance().getUsers().get(2).getName().equals("HEllo"));
    assertTrue(Wave.getInstance().getUsers().get(2).getCoins() == 20);
    assertTrue(Wave.getInstance().getUsers().get(2).getShip() == ShipSkins.SHIP1);
    assertTrue(Wave.getInstance().getUsers().get(2).getOwnedShipSkins().size() == 1);
    assertTrue(Wave.getInstance().getUsers().get(2).getOwnedShipSkins().get(0) == ShipSkins.SHIP1);


  }
  
}
