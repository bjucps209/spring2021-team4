package model.GameObjects;

public abstract class GameObject {
    // abstract class for all game objects
    protected int x;
    protected int y;
    protected int dx; // delta X (added to x every update (speed))
    protected int dy; // delta y (added to y every update (speed))

    public abstract String serialize();
    public abstract void deserialize(String info);
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
}