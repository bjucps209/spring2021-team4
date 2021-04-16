package model.GameObjects.Enemies;

import model.Level;
import model.GameObjects.Player;

public class Tracker extends EnemyObject {
    public static int speed = 3;
    
    public Tracker(Level l) {
        super(l);
        setDy(Tracker.speed);
        setDx(Tracker.speed);
    }

    @Override
    public void update() {
        super.update();
        calcDirection(player);
    }

    public void calcDirection(Player p) {
        int angle;
        int x1 = this.getX();
        int x2 = p.getX();
        int y1 = this.getY();
        int y2 = p.getY();
        int adjacent = x1 - x2;
        int opposite = y1 - y2;
        if (adjacent < 0) {
            if (opposite < 0) {
                angle = 45;
            } else if (opposite > 0) {
                angle = 315;
            } else {
                angle = 0;
            }
        } else if (adjacent > 0) {
            if (opposite < 0) {
                angle = 135;
            } else if (opposite > 0) {
                angle = 225;
            } else {
                angle = 180;
            }
        } else {
            if (opposite < 0) {
                angle = 90;
            } else {
                angle = 270;
            }
        }
        switch (angle) {
            case 0:
                setDx(Tracker.speed);
                setDy(0);
                break;
            case 45:
                setDx(Tracker.speed);
                setDy(-Tracker.speed);
                break;
            case 90:
                setDx(0);
                setDy(-Tracker.speed);
                break;
            case 135:
                setDx(-Tracker.speed);
                setDy(-Tracker.speed);
                break;
            case 180:
                setDx(-Tracker.speed);
                setDy(0);
                break;
            case 225:
                setDx(-Tracker.speed);
                setDy(Tracker.speed);
                break;
            case 270:
                setDx(0);
                setDy(Tracker.speed);
                break;
            case 315:
                setDx(Tracker.speed);
                setDy(Tracker.speed);
                break;
            default:
                System.out.println("error");
                break;
        }
    }
}
