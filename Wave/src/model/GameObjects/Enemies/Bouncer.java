//-----------------------------------------------------------
//File:   Bouncer.java
//Desc:   entity that does not track, only bounces off of 
//        obstacles and walls
//-----------------------------------------------------------

package model.GameObjects.Enemies;

import model.Level;

// enemy entity that bounces off of obstacles and walls but has no tracking capabilities
public class Bouncer extends EnemyObject {

    public static int speed = 5;

    public Bouncer(Level l) {
        super(l);
        setWidth(50);
        setHeight(50);
        hitDetection = new Thread(() -> {
            while (true) {
                checkWallCollision();
                if (currentLevel.getObstacles() != null && checkCollision(currentLevel.getObstacles()) != null) {
                    hits.add(checkCollision(currentLevel.getObstacles()));
                }
                if (hits.size() != 0) {
                    processHit(hits.get(0), this, currentLevel.getPlayer());
                    hits.remove(hits.get(0));
                }
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ;
    }
}
