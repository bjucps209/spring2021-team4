package model.GameObjects.Enemies;


import model.Level;
import model.Enums.EnemyTypes;
import model.GameObjects.GameObject;

public abstract class EnemyObject extends GameObject {
    // abstract class for Enemy objects in game
    private EnemyTypes type;
    private boolean temporaryFreeze = false;
        

    public EnemyObject(Level l) {
        super(l);
    }

    public EnemyTypes getType() {
        return type;
    }

    public void setType(EnemyTypes type) {
        this.type = type;
    }

    public static EnemyObject create(EnemyTypes e, Level l) {
        EnemyObject enemy;
        switch (e) {
        case BOUNCER:
            enemy = new Bouncer(l);
            enemy.setDx(5);
            enemy.setDy(5);
            enemy.setType(e);
            break;
        case GHOST:
            enemy = new Bouncer(l);
            enemy.setType(EnemyTypes.BOUNCER);
            break;
        case LASER:
            enemy = new Bouncer(l);
            enemy.setType(EnemyTypes.BOUNCER);
            break;
        case SHAPESHIFTER:
            enemy = new Bouncer(l);
            enemy.setType(EnemyTypes.BOUNCER);
            break;
        case TRACKER:
            enemy = new Bouncer(l);
            enemy.setType(EnemyTypes.BOUNCER);
            break;
        default:
            enemy = new Bouncer(l);
            enemy.setType(EnemyTypes.BOUNCER);
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

    public boolean isTemporaryFreeze() {
        return temporaryFreeze;
    }

    public void setTemporaryFreeze(boolean temporaryFreeze) {
        this.temporaryFreeze = temporaryFreeze;
    }
}
