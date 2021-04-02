package model.GameObjects;

public abstract class GameObject {
    // abstract class for all game objects
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;

    public abstract String serialize();
    public abstract void deserialize(String info);
}