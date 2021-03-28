import java.io.IOException;
import java.util.ArrayList;

public class Gate extends Field {
    private boolean working;
    private boolean wild;
    private Gate pair;

    public Gate() {
        working = false;
        wild = false;
    }

    public boolean getWorking() {
        /*Skeleton.printFunc();
        System.out.println("working? y/n");
        String ans = Skeleton.getUserInput();
        if (ans.contains("y")) {
            Skeleton.printFuncRet("true");
            return true;
        } else {
            Skeleton.printFuncRet("false");
            return false;
        }*/
        return working;
    }

    public boolean pickedUpBy(Ship ship) {
        /*Skeleton.printFunc();
        System.out.println("2>gates? y/n");
		String ans = Skeleton.getUserInput();
		if(ans.contains("y")) {
			this.setWorking(false);
			ship.addGate(this);
        	Skeleton.printFuncRet("true");
        	return true;
		}else {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns"+" false");
			return false;}*/
        ship.addGate(this);
        for(int i = neighbours.size()-1; i >= 0; i--){
            neighbours.get(i).removeNeighbour(this);
        }
        ship.move();
        setWorking(false);
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

    public void setWorking(boolean _working) {
        Skeleton.printFunc();
        working = _working;
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

    public void onSolarStorm() {
        for (int i = onSurface.size()-1; i >= 0; i--)
            onSurface.get(i).onSolarStormCase();
    }

}
