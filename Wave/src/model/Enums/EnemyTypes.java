//-----------------------------------------------------------
//File:   EnemyTypes.java
//Desc:   enumeration for each of the 3 enemy types
//        
//-----------------------------------------------------------

package model.Enums;

public enum EnemyTypes {
    BOUNCER,  // Bounce off the wall and obstacle
    TRACKER,  // Track player, do not affect by obstacle
    GHOST,    // bounce like Bouncer, but also does not affect by obstacles
}
