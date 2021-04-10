package model.GameObjects;

import model.Wave;

public abstract class GameObject {
    // abstract class for all game objects
    protected int x;
    protected int y;
    protected int dx; // delta X (added to x every update (speed))
    protected int dy; // delta y (added to y every update (speed))
    protected int width;
    protected int height;

    public abstract String serialize();
    public abstract boolean deserialize(String info);

    public void GameObject() {

    }

    // update method each object needs
    public void update() {
        checkWallCollision();
        x = x + dx;
        y = y + dy;
    } 

    public void checkWallCollision() {
        if (x <= 4 || x >= Wave.getInstance().getGame().getGameWidth() + width - 4) {
            dx = -dx;
        } else if (y <= 4 || y >= Wave.getInstance().getGame().getGameHeight() + height - 4) {
            dy = -dy;
        }
    }

    @Override
    public String toString() {
        String template = "x : %s \n y : %s \n dx : %s \n dy : %s \n";
        String s = String.format(template, x, y, dx, dy);
        return s; 
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getDx() {
        return dx;
    }
    public void setDx(int dx) {
        this.dx = dx;
    }
    public int getDy() {
        return dy;
    }
    public void setDy(int dy) {
        this.dy = dy;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}