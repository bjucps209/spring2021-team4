package model.GameObjects.Enemies;

import model.Level;

public class Bouncer extends EnemyObject {
    public Bouncer(Level l) {
        super(l);
        setWidth(50);
        setHeight(50);
    }

    public static int speed = 5;
}
