package model.GameObjects.Enemies;

import model.Level;

public class Bouncer extends EnemyObject {
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
                //else if (checkCollision(currentLevel.getPowerUps()) != null) {
                    //hits.add(checkCollision(currentLevel.getPowerUps()));
                //}
                
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
        });;
    }

    public static int speed = 5;
}
