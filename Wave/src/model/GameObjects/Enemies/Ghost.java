package model.GameObjects.Enemies;

import model.Level;

public class Ghost extends EnemyObject {

    public static int speed = 2;

    public Ghost(Level l) {
        super(l);
        this.setDx(Ghost.speed);
        this.setDy(Ghost.speed);
        this.setWidth(50);
        this.setHeight(50);
    } 
}
