//-----------------------------------------------------------
//File:   EnemyObject.java
//Desc:   class that all enemyobjects inherit from
//        
//-----------------------------------------------------------

package model.GameObjects.Enemies;

import model.Level;
import model.Enums.EnemyTypes;
import model.GameObjects.GameObject;
import model.GameObjects.Player;

// abstract class for Enemy objects in game
public abstract class EnemyObject extends GameObject {
 
    protected EnemyTypes type;  // A enum indicate this enemy type
    protected boolean temporaryFreeze = false;  // boolean value indicate is the enemy temporary freeze by Freeze powerUp
    protected Player player;   // The current player at this level 
    protected boolean isRemoved = false;  // Bpolean indicate should this enemobject be remove from the screen, 
                                            // cause by the destroyship methods that randomly remove one enemy from the screen

    public EnemyObject(Level l) {
        super(l);
        player = currentLevel.getPlayer();

        // TODO: in future version, control when will enemy come in
        // right now set default to 60 second, the beginning of game
        appearTime = 60;
    }

    @Override
    public void update() {
        if (temporaryFreeze == false) {
            super.update();
        }

    }

    public EnemyTypes getType() {
        return type;
    }

    public void setType(EnemyTypes type) {
        this.type = type;
    }

    /**
     * A factory method for all enemyobjects
     * 
     * @param type the type of enemyobject to be created
     * @param l    the level this powerup is being created for
     * @return an instance of the respective enemyobject type
     */
    public static EnemyObject create(EnemyTypes e, Level l) {
        EnemyObject enemy;
        switch (e) {
        case BOUNCER:
            enemy = new Bouncer(l);
            enemy.setDx(Bouncer.speed + increaseSpeed);
            enemy.setDy(Bouncer.speed + increaseSpeed);
            enemy.setType(e);
            break;
        case GHOST:
            enemy = new Ghost(l);
            enemy.setDx(Ghost.speed + increaseSpeed);
            enemy.setDy(Ghost.speed + increaseSpeed);
            enemy.setType(e);
            break;
        case TRACKER:
            enemy = new Tracker(l);
            enemy.setType(e);
            enemy.setDx(Tracker.speed + increaseSpeed);
            enemy.setDy(Tracker.speed + increaseSpeed);
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
        return "EnemyObject" + ";" + type.toString() + ";" + x.get() + ";" + y.get() + ";" + width.get() + ";"
                + height.get() + ";" + dx.get() + ";" + dy.get() + ";" + appearTime;
        // TODO: special affect on enemy Object
    }

    @Override
    public boolean deserialize(String info) {
        // TODO Auto-generated method stub
        // TODO handle special effects later on
        try {
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
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isTemporaryFreeze() {
        return temporaryFreeze;
    }

    public void setTemporaryFreeze(boolean temporaryFreeze) {
        this.temporaryFreeze = temporaryFreeze;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }
}
