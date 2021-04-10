import java.util.ArrayList;

public abstract class FlyingObject implements Steppable {
    protected Field location;

    public FlyingObject() {
    }

    public Field getLocation() {
        Skeleton.printFunc();
        Skeleton.printFuncRet("");
        return location;
    }

    public void setLocation(Field newLocation) {
        Skeleton.printFunc();
        location = newLocation;
        Skeleton.printFuncRet("");
    }

    public boolean drill() {
        Skeleton.printFunc();
        boolean to_return=location.onDrill();
        Skeleton.printFuncRet(Boolean.toString(to_return));
        return to_return;
    }
    public boolean addMaterial(Material material) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }

    public abstract boolean move();

    public abstract void die();

    public abstract void onExplode();

    public abstract void onSolarStormCase();
}
