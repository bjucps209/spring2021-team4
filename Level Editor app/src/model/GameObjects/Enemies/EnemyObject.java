package model.GameObjects.Enemies;

import model.Enums.EnemyTypes;
import model.GameObjects.GameObject;

public abstract class EnemyObject extends GameObject {
    // abstract class for Enemy objects in game
    private EnemyTypes type;

    public EnemyTypes getType() {
        return type;
    }

    public void setType(EnemyTypes type) {
        this.type = type;
    }

    public static EnemyObject create(EnemyTypes e) {
        EnemyObject enemy;
        switch (e) {
        case BOUNCER:
            enemy = new Bouncer();
            enemy.setDx(5);
            enemy.setDy(5);
            enemy.setType(e);
            break;
        case GHOST:
            enemy = new Ghost();
            enemy.setType(e);
            break;
        case TRACKER:
            enemy = new Tracker();
            enemy.setType(e);
            break;
        default:
            enemy = new Bouncer();
            enemy.setType(e);
            break;
        }
        return enemy;
    }

    @Override
    public String serialize() {
        // TODO Auto-generated method stub
        return "EnemyObject"+";"+type.toString()+";"+x.get()+";"+y.get()+";" + width.get() +";" + height.get()+";"+dx.get()+";"+dy.get();  // TODO: special affect on enemy Object
    }

    @Override
    public boolean deserialize(String info) {
        // TODO Auto-generated method stub
        // TODO handle special effects later on
        try{
            String[] enemyInfo = info.split(";");
        // x,y,width,height,dx,dy,special affect
        // type already been set in level.serialization()
        this.x.set(Integer.parseInt(enemyInfo[0]));
        this.y.set(Integer.parseInt(enemyInfo[1]));
        this.width.set(Integer.parseInt(enemyInfo[2]));
        this.height.set(Integer.parseInt(enemyInfo[3]));
        this.dx.set(Integer.parseInt(enemyInfo[4]));
        this.dy.set(Integer.parseInt(enemyInfo[5]));
        return true;
        }catch (Exception e){
            return false;
        }
        

        
    }
}
