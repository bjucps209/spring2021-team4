package model.GameObjects;


public class Obstacle extends GameObject {
    // class for each Obstacle instance during the game


    @Override
    public String serialize() {
        // TODO Auto-generated method stub
        // TODO: do we have different type of obstacles

        return "Obstacle;"+"Obstacle"+";"+x+";"+y+";" + width+";" + height+";"+dx+";"+dy; //TODO: special affect on obstacles?
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
        this.x = Integer.parseInt(restInfo[0]);
        this.y = Integer.parseInt(restInfo[1]);
        this.width = Integer.parseInt(restInfo[2]);
        this.height = Integer.parseInt(restInfo[3]);
        this.dx = Integer.parseInt(restInfo[4]);
        this.dy = Integer.parseInt(restInfo[5]);

        return true;
        } catch (Exception e){
            return false; // error in converting obstacle data
        }
    }  
}
