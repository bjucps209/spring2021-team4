package model.GameObjects;

public class Player extends GameObject {
    // contains info for a Player during the game
    
    // instance variables
    private int health;
    private int speed = 5; // speed that dx and dy should be (0 or whatever speed is)

    public Player() {
        super();
        x = 40;
        y = 5;
        dx = 0;
        dy = 0;
        width = 5;
        height = 5;
    }

    public void moveUp() {
        this.dy = -speed;
    }

    public void moveDown() {
        this.dy = speed;
    }

    public void moveLeft() {
        this.dx = -speed;
    }

    public void moveRight() {
        this.dx = speed;
    }

    public void moveNeutral() {
        this.dx = 0;
        this.dy = 0;
    }

    @Override
    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deserialize(String info) {
        // TODO Auto-generated method stub
        
    }

}
