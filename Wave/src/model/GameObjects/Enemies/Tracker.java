package model.GameObjects.Enemies;

import model.Level;
import model.GameObjects.Player;

public class Tracker extends EnemyObject {
    public static int speed = 2;
    
    public Tracker(Level l) {
        super(l);
        setDy(3);
        setDx(3);
        setWidth(50);
        setHeight(50);
        hitDetection = new Thread(() -> {
            while (true) {
            checkWallCollision();
            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }});
    }

    @Override
    public void update() {
        calcDirection(player);
        super.update();
    }

    public void calcDirection(Player p) {
        int angle;
        int x1 = this.getX();
        int x2 = p.getX();
        int y1 = this.getY();
        int y2 = p.getY();
        int adjacent = x1 - x2;
        int opposite = y1 - y2;
        // if object is to the right
        if (adjacent < 0) {
            // if object is below
            if (opposite < 0) {
                angle = 315;
            // if object is above
            } else if (opposite > 0) {
                angle = 45;
            // if object is to the right and not above or below
            } else {
                angle = 0;
            }
        // if object is to the left
        } else if (adjacent > 0) {
            // if object is below
            if (opposite < 0) {
                angle = 225;
            // if object is above
            } else if (opposite > 0) {
                angle = 135;
            // if object is to the left and not above or below
            } else {
                angle = 180;
            }
        // if object is not to the left or right
        } else {
            // if object is below
            if (opposite < 0) {
                angle = 270;
            // if object is above
            } else {
                angle = 90;
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
                setDy(+Tracker.speed);
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
