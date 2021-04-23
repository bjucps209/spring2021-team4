package model.GameObjects.Enemies;

import model.Level;
import model.Enums.EnemyTypes;
import model.GameObjects.Player;

import java.util.ArrayList;
import java.util.Random;

public class Shapeshifter extends EnemyObject {

    // potentially keep track of an instance of each of these? would this make the person take triple the damage?
    Bouncer bouncer;
    Tracker tracker;
    Ghost ghost;


    Random random;
    ArrayList<EnemyTypes> switchChoices = new ArrayList<>();

    int timer;
    public static int speed = 4;

    public Shapeshifter(Level l) {
        super(l);
        // this.bouncer = new Bouncer(l);
        // this.tracker = new Tracker(l);
        // this.ghost = new Ghost(l);
        this.type = EnemyTypes.BOUNCER;
        switchChoices.add(EnemyTypes.GHOST);
        switchChoices.add(EnemyTypes.TRACKER);
        // this.random = new Random();
        
    }

    // this needs to happen if the timer equals 10;
    public void switchType() {
        int choice = new Random().nextInt(2);
        EnemyTypes newType = switchChoices.get(choice);
        switchChoices.remove(choice);
        switchChoices.add(this.type);
        this.type = newType;
        timer = 0;
    }

    // im going to copy the update methods from each entity into here
    @Override
    public void update() {
        if(temporaryFreeze == false){
            if (this.type.equals(EnemyTypes.BOUNCER)) {
            super.update();
            }
            else if (this.type.equals(EnemyTypes.TRACKER)) {
                // also add in obstacle hit detection
                calcDirection(player);
                super.update();
            }
            else {
                calcDirection(player);
                super.update();
            }
        }
  
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
