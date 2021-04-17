package model.GameObjects.Enemies;


import model.Level;
import model.Enums.EnemyTypes;
import model.GameObjects.GameObject;
import model.GameObjects.Player;

public abstract class EnemyObject extends GameObject {
    // abstract class for Enemy objects in game
    protected EnemyTypes type;
    protected boolean temporaryFreeze = false;
    protected Player player;
        

    public EnemyObject(Level l) {
        super(l);
        player = currentLevel.getPlayer();

        //TODO: in future version, control when will enemy come in
        // right now set default to 60 second, the beginning of game
        appearTime = 60;
    }

    @Override
    public void update() {
        super.update();
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
            enemy.setDx(Bouncer.speed);
            enemy.setDy(Bouncer.speed);
            enemy.setType(e);
            break;
        case GHOST:
            enemy = new Ghost(l);
            enemy.setType(e);
            break;
        case LASER:
            enemy = new Laser(l);
            enemy.setType(e);
            break;
        case SHAPESHIFTER:
            enemy = new Shapeshifter(l);
            enemy.setType(e);
            break;
        case TRACKER:
            enemy = new Tracker(l);
            enemy.setType(e);
            enemy.setDx(Tracker.speed);
            enemy.setDy(Tracker.speed);
            break;
        default:
            enemy = new Bouncer(l);
            enemy.setType(e);
            break;
        }
        return enemy;
    }

    @Override
    public String serialize() {
        // TODO Auto-generated method stub
        return "EnemyObject"+";"+type.toString()+";"+x.get()+";"+y.get()+";" + width.get() +";" + height.get()+";"+dx.get()+";"+dy.get()+";"+appearTime;
          // TODO: special affect on enemy Object
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
        this.appearTime = Integer.parseInt(enemyInfo[6]);
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
