import java.util.ArrayList;
import java.util.Random;

public class Robot extends FlyingObject {
    public Robot() {
        location.acceptFlyingObject(this);
        game.addGameObject(this);
    }

    public void step() {
        boolean done = false;
        done = drill();
        if (!done)
            move();
    }

    public boolean move() {
        ArrayList<Field> moveable = new ArrayList<Field>();
        moveable = location.getNeighbours();
        Field newLocation;
        if (moveable.size() != 0) {
            Random random = new Random();
            int newLocationInt = random.nextInt(moveable.size());
            newLocation = moveable.get(newLocationInt);
            location.removeFlyingObject(this);
            newLocation.acceptFlyingObject(this);
            System.out.println("A mozgás sikeres!");
            return true;
        }
        System.out.println("A mozgás sikertelen, mert nincs szomszéd!");
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
