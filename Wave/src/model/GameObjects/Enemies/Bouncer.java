package model.GameObjects.Enemies;

import model.Level;
import model.Wave;

public class Bouncer extends EnemyObject {
    public Bouncer(Level l) {
        super(l);
    }

    public static int speed = 5;
}
