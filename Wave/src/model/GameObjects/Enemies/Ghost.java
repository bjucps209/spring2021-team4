//-----------------------------------------------------------
//File:   Ghost.java
//Desc:   tracks the player but does not interact with obstacles
//        
//-----------------------------------------------------------

package model.GameObjects.Enemies;

import model.Level;

// class that can follow a player but doesnt interact with obstacles
public class Ghost extends EnemyObject {

    public static int speed = 2;

    public Ghost(Level l) {
        super(l);
        this.setWidth(50);
        this.setHeight(50);
    }
}
