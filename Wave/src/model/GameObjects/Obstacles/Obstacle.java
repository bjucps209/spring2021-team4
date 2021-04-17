
package model.GameObjects.Obstacles;
import model.Level;
import model.Enums.ObstacleTypes;
import model.GameObjects.GameObject;

public class Obstacle extends GameObject {
    // class for each Obstacle instance during the game
    protected ObstacleTypes type;
    public Obstacle(Level l){
        super(l);
        this.dx.set(0);
        this.dy.set(0);
        //TODO: specific width and height for different obstacles

        appearTime = 60;
    }
    public Obstacle(ObstacleTypes type, Level l) {
        super(l);
        this.dx.set(0);
        this.dy.set(0);
        this.appearTime = 60;
    }
    
    public static Obstacle create(ObstacleTypes type, Level l){
        Obstacle ob;
        switch(type){
            case SQUARE:{
                ob = new Square(l);
                break;
            }
            case LARGE:{
                ob = new Large(l);
                break;
            }
            case NARROW:{
                ob = new Narrow(l);
            }
            case CORNER:{
                ob = new Corner(l);
            }
            default:
                // should no happen
                ob = null;
        }

        return ob;
    }

    @Override
    public void update() {

        // Obstacles will not move
    }
    @Override
    public String serialize() {
        // TODO Auto-generated method stub
        // TODO: do we have different type of obstacles

        return "Obstacle;"+"Obstacle"+";"+x.get()+";"+y.get()+";" + width.get()+";" + height.get()+";"+dx.get()+";"+dy.get() + ";"+appearTime;
         //TODO: special affect on obstacles?
    }

    @Override
    public boolean deserialize(String info) {
        // info contains
        // x,y,width,height,dx,dy,special effect
        // TODO Auto-generated method stub
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