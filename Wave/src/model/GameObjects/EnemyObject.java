package model.GameObjects;

import model.Enums.EnemyTypes;

public abstract class EnemyObject extends GameObject {
    // abstract class for Enemy objects in game
    private EnemyTypes type;

    public EnemyTypes getType() {
        return type;
    }

    public void setType(EnemyTypes type) {
        this.type = type;
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
