package model.GameObjects;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Wave;
import model.GameObjects.Enemies.EnemyObject;

public abstract class GameObject {
    // abstract class for all game objects
    protected IntegerProperty x = new SimpleIntegerProperty();
    protected IntegerProperty y = new SimpleIntegerProperty();
    protected IntegerProperty dx = new SimpleIntegerProperty(); // delta X (added to x every update (speed))
    protected IntegerProperty dy = new SimpleIntegerProperty(); // delta y (added to y every update (speed))
    protected IntegerProperty width = new SimpleIntegerProperty();
    protected IntegerProperty height = new SimpleIntegerProperty();
    protected static IntegerProperty speed = new SimpleIntegerProperty();

    public abstract String serialize();
    public abstract boolean deserialize(String info);

    ArrayList<Obstacle> obstacles;
    ArrayList<EnemyObject> enemies;


    
    int collisionNum = 0;

    public void GameObject() {

    }

    // update method each object needs
    public void update() {
        collisionNum++;
        if (collisionNum % 10 == 0) {
            checkWallCollision();
            checkObstacleCollision();
        } 
        x.set(getX() + getDx());
        y.set(getY() + getDy());
    } 

    public void checkWallCollision() {
        if (getX() <= 4 || getX() >= Wave.getInstance().getGame().getGameWidth() - getWidth()) {
            dx.set(-getDx());
            if (getX() < 10) {
                x.set(1);
            } else {
                x.set(Wave.getInstance().getGame().getGameWidth() - getWidth() - 1);
            }
        } else if (getY() <= 4 || getY() >= Wave.getInstance().getGame().getGameHeight() - getHeight()) {
            dy.set(-getDy());
            if (getY() < 10) {
                y.set(1);
            } else {
                y.set(Wave.getInstance().getGame().getGameHeight() - getHeight() - 1);
            }
        }
    }

    public void checkObstacleCollision() {
        obstacles = Wave.getInstance().getGame().getCurrentLevel().getObstacles();
        for (Obstacle e : obstacles) {
            for (int i = e.getX(); i <= e.getX() + e.getWidth(); i++) {
                for (int k = getX(); k <= getX() + getWidth(); k++) {
                    if (k == i) {
                        for (int j = e.getY(); j <= e.getY() + e.getHeight(); j++) {
                            for (int l = getY(); l <= getY() + getHeight(); l++) {
                                if (l == j) {
                                    //TODO HIT
                                    System.out.println("HIT!");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    

    @Override
    public String toString() {
        String template = "x : %s \n y : %s \n dx : %s \n dy : %s \n";
        String s = String.format(template, getX(), getY(), getDx(), getDy());
        return s; 
    }

    public int getX() {
        return this.x.get();
    }
    public void setX(int x) {
        this.x.set(x);
    }
    public int getY() {
        return this.y.get();
    }
    public void setY(int y) {
        this.y.set(y);
    }
    public int getDx() {
        return this.dx.get();
    }
    public void setDx(int dx) {
        this.dx.set(dx);
    }
    public int getDy() {
        return this.dy.get();
    }
    public void setDy(int dy) {
        this.dy.set(dy);
    }
    public int getWidth() {
        return this.width.get();
    }
    public void setWidth(int width) {
        this.width.set(width);
    }
    public int getHeight() {
        return this.height.get();
    }
    public void setHeight(int height) {
        this.height.set(height);
    }

    public int getSpeed() {
        return this.speed.get();
    }

    public void setSpeed(int speed) {
        this.speed.set(speed);
    }

    public IntegerProperty speed() {
        return speed;
    }

    public IntegerProperty xProperty() {
        return x;
    }
    public IntegerProperty yProperty() {
        return y;
    }
    public IntegerProperty dxProperty() {
        return dx;
    }
    public IntegerProperty dyProperty() {
        return dy;
    }
    public IntegerProperty widthProperty() {
        return width;
    }
    public IntegerProperty heightProperty() {
        return height;
    }
}