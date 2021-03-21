import java.io.IOException;
import java.util.ArrayList;

public class Gate extends Field {
    private boolean working;
    private Gate pair;

    public Gate() {
        working = false;
    }

    public boolean getWorking() {
        Skeleton.printFunc();
        System.out.println("working? y/n");
        String ans = Skeleton.getUserInput();
        if (ans.contains("y")) {
            Skeleton.printFuncRet("true");
            return true;
        } else {
            Skeleton.printFuncRet("false");
            return false;
        }

    }

    public boolean pickedUpBy(Ship ship) {
        Skeleton.printFunc();
        ship.addGate(this);
        Skeleton.printFuncRet("true");
        return true;
    }

    public boolean teleport(FlyingObject f) {
        Skeleton.printFunc();

        if (pair.getWorking()) {
            removeFlyingObject(f);
            pair.acceptFlyingObject(f);
            Skeleton.printFuncRet("true");
            return true;
        }
        Skeleton.printFuncRet("false");
        return false;
    }

    public static ArrayList<Material> craftGateReq() {
        Skeleton.printFunc();
        ArrayList<Material> needed = new ArrayList<>();
        needed.add(new Iron());
        needed.add(new Iron());
        needed.add(new Ice());
        needed.add(new Uranium());
        Skeleton.printFuncRet("needed");
        return needed;
    }

    public void setWorking(boolean working) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("");
    }

    public Gate getPair() {
        Skeleton.printFunc();
        Skeleton.printFuncRet("");
        return pair;
    }

    public void setPair(Gate pair) {
        Skeleton.printFunc();
        this.pair = pair;
        Skeleton.printFuncRet("");
    }

    public boolean onDrill() {
        Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }

    public void onSolarStorm() {
        for (FlyingObject flyingObject : onSurface) flyingObject.onSolarStormCase();
    }

    public boolean onMine(Ship ship) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }

    public boolean fillBy(Ship ship) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }
}
