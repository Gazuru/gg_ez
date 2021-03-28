import java.util.ArrayList;

public abstract class FlyingObject implements Steppable {
    protected Field location;
    protected ArrayList<Material> materials;

    public FlyingObject() {

        materials = new ArrayList<Material>();

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

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<Material> newMaterials) {
        materials = newMaterials;
    }


    public boolean drill() {
        Skeleton.printFunc();
        boolean completed = location.onDrill();
        Skeleton.printFuncRet(Boolean.toString(completed));
        return completed;
    }

    public abstract boolean move();

    public abstract void die();

    public abstract void onExplode();

    public abstract void onSolarStormCase();
}
