import java.util.ArrayList;
import java.util.Random;

public class Robot extends FlyingObject {
    public Robot() {
        location.acceptFlyingObject(this);
    }

    public void step() {
        boolean done = false;
        done = drill();
        if (!done)
            move();
    }

    public boolean move() {
        ArrayList<Field> current_neighbours = location.getNeighbours();
        if (current_neighbours.size() != 0) {
            Random random = new Random();
            int newLocationInt = random.nextInt(current_neighbours.size());
            Field newLocation = current_neighbours.get(newLocationInt);
            
            
            location.removeFlyingObject(this);
            newLocation.acceptFlyingObject(this);
            return true;
        }
        return false;
    }

    public void die() {
        Skeleton.printFunc();
        location.removeFlyingObject(this);
        Game.removeGameObject(this);
        Skeleton.printFuncRet("");
    }

    public void onExplode() {
        move();
    }

    public void onSolarStormCase() {
    	Skeleton.printFunc();
    	die();
        Skeleton.printFuncRet("");
    }

    public static ArrayList<Material> craftRobotReq() {
        Skeleton.printFunc();
        ArrayList<Material> robotReq = new ArrayList<Material>();
        robotReq.add(new Iron());
        robotReq.add(new Coal());
        robotReq.add(new Uranium());
        Skeleton.printFuncRet("");
        return robotReq;
    }
}
