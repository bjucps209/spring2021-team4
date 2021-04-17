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
        hitDetection = new Thread(() -> {
            while (true) {
                checkWallCollision();
                if (currentLevel.getObstacles() != null && checkCollision(currentLevel.getObstacles()) != null) {
                    hits.add(checkCollision(currentLevel.getEnemies()));
                } 
                //else if (checkCollision(currentLevel.getPowerUps()) != null) {
                    //hits.add(checkCollision(currentLevel.getPowerUps()));
                //}
                
                if (hits.size() != 0) {
                    processHit(hits.get(0), currentLevel.getPlayer());
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
}
