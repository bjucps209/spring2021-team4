package model.GameObjects;

public abstract class GameObject {
    // abstract class for all game objects
    public abstract String serialize();
    public abstract void deserialize(String info);
}