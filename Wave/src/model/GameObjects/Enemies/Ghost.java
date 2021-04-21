package model.GameObjects.Enemies;

import model.Level;

public class Ghost extends EnemyObject {

    public static int speed = 2;

    public Ghost(Level l) {
        super(l);
        this.setWidth(50);
        this.setHeight(50);
    } 
}
