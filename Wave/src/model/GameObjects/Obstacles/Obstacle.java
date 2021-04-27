//-----------------------------------------------------------
//File:   Obstacle.java
//Desc:    file that all obstacles inherit from
//        
//-----------------------------------------------------------

package model.GameObjects.Obstacles;
import model.Level;
import model.Enums.ObstacleTypes;
import model.GameObjects.GameObject;

// abstract class for each Obstacle instance during the game
public class Obstacle extends GameObject {

    protected ObstacleTypes type;
    public Obstacle(Level l){
        super(l);
        this.dx.set(0);
        this.dy.set(0);
        //TODO: specific width and height for different obstacles

        appearTime = 60;
        hitDetection = new Thread(() -> {
            while (true) {
                checkWallCollision();
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
        });
    }

    /**
     * A factory method for all obstacles
     * @param type the type of obstacle to be created
     * @param l the level this powerup is being created for
     * @return an instance of the respective obstacle type
     */
    public static Obstacle create(ObstacleTypes type, Level l) {
        Obstacle obstacle;
        switch (type) {
            case SQUARE:
                obstacle = new Square(l);
                obstacle.setWidth(50);
                obstacle.setHeight(50);
                break;
            case NARROW:
                obstacle = new Narrow(l);
                //TODO: set width and height
                break;
            // case CORNER:
            //     obstacle = new Corner(l);
            //     //TODO: set width and height        
                // break;
            case LARGE:
                obstacle = new Large(l);
                //TODO: set width and height
                break;
            default:
                obstacle = null;
                break;
        }
        return obstacle;
    }

    @Override
    public void update() {

        // Obstacles will not move
    }
    @Override
    public String serialize() {
        // TODO: do we have different type of obstacles

        return "Obstacle;"+type.toString()+";"+x.get()+";"+y.get()+";" + width.get()+";" + height.get()+";"+dx.get()+";"+dy.get() + ";"+appearTime;
    }

    @Override
    public boolean deserialize(String info) {
        // info contains
        // x,y,width,height,dx,dy,special effect
        // TODO handle special effects
        try{
        String [] restInfo = info.split(";");
        //x;y;dx;dy;special effect
        this.x.set(Integer.parseInt(restInfo[0]));
        this.y.set(Integer.parseInt(restInfo[1]));
        this.width.set(Integer.parseInt(restInfo[2]));
        this.height.set(Integer.parseInt(restInfo[3]));
        this.dx.set(Integer.parseInt(restInfo[4]));
        this.dy.set(Integer.parseInt(restInfo[5]));
        this.appearTime = Integer.parseInt(restInfo[6]);

        return true;
        } catch (Exception e){
            return false; // error in converting obstacle data
        }
    }
    public ObstacleTypes getType() {
        return type;
    }
    public void setType(ObstacleTypes type) {
        this.type = type;
    }  
}