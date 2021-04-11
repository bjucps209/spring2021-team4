package model.GameObjects;

import org.junit.rules.TestRule;

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

    public static EnemyObject create(EnemyTypes e) {
        EnemyObject enemy;
        switch (e) {
        case BOUNCER:
            enemy = new Bouncer();
            enemy.setDx(5);
            enemy.setDy(5);
            enemy.setType(EnemyTypes.BOUNCER);
            break;
        case GHOST:
            enemy = new Bouncer();
            enemy.setType(EnemyTypes.BOUNCER);
            break;
        case LASER:
            enemy = new Bouncer();
            enemy.setType(EnemyTypes.BOUNCER);
            break;
        case SHAPESHIFTER:
            enemy = new Bouncer();
            enemy.setType(EnemyTypes.BOUNCER);
            break;
        case TRACKER:
            enemy = new Bouncer();
            enemy.setType(EnemyTypes.BOUNCER);
            break;
        default:
            enemy = new Bouncer();
            enemy.setType(EnemyTypes.BOUNCER);
            break;
        }
        return enemy;
    }

    @Override
    public String serialize() {
        // TODO Auto-generated method stub
        return "EnemyObject"+";"+type.toString()+";"+x+";"+y+";" + width +";" + height+";"+dx+";"+dy;  // TODO: special affect on enemy Object
    }

    @Override
    public boolean deserialize(String info) {
        // TODO Auto-generated method stub
        // TODO handle special effects later on
        try{
            String[] enemyInfo = info.split(";");
        // x,y,width,height,dx,dy,special affect
        // type already been set in level.serialization()
        this.x = Integer.parseInt(enemyInfo[0]);
        this.y = Integer.parseInt(enemyInfo[1]);
        this.width = Integer.parseInt(enemyInfo[2]);
        this.height = Integer.parseInt(enemyInfo[3]);
        this.dx = Integer.parseInt(enemyInfo[4]);
        this.dy = Integer.parseInt(enemyInfo[5]);
        return true;
        }catch (Exception e){
            return false;
        }
        

        
    }
}
