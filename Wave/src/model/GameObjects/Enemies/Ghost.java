package model.GameObjects.Enemies;

import model.Level;

public class Ghost extends EnemyObject {

    public Ghost(Level l) {
        super(l);
        hitDetection = new Thread(() -> {
            while (true) {
                checkWallCollision();
                if (checkCollision(currentLevel.getObstacles()) != null) {
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

    public static int speed = 3;
}
